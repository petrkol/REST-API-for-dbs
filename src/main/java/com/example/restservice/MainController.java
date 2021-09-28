package com.example.restservice;

import javax.sql.DataSource;

import com.example.objects.DBSchema;
import com.example.objects.Statistics;
import com.example.utils.Constants;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/v1")
public class MainController {

  @Autowired
  private ConnectionRepository connectionRep;

  @Autowired
  private DataSource dataSource;

  @PostMapping(path = "/add")
  public @ResponseBody String addNewConnection(@RequestParam String name, @RequestParam String hostName,
      @RequestParam Integer port, @RequestParam String databaseName, @RequestParam String userName,
      @RequestParam String password) {

    DBConnection con = new DBConnection();
    con.setName(name);
    con.setHostName(hostName);
    con.setPort(port);
    con.setDatabaseName(databaseName);
    con.setUserName(userName);
    con.setPassword(password);
    connectionRep.save(con);
    return "Done";
  }

  @PostMapping(path = "/update")
  public @ResponseBody String updateConnection(@RequestParam Integer id, @RequestParam String name,
      @RequestParam String hostName, @RequestParam Integer port, @RequestParam String databaseName,
      @RequestParam String userName, @RequestParam String password) {
    DBConnection con = new DBConnection();
    con.setId(id);
    con.setName(name);
    con.setHostName(hostName);
    con.setPort(port);
    con.setDatabaseName(databaseName);
    con.setUserName(userName);
    con.setPassword(password);
    connectionRep.save(con);
    return "Done";
  }

  @PostMapping(path = "/delete")
  public @ResponseBody String deleteConnection(@RequestParam Integer id) {
    connectionRep.deleteById(id);
    return "Done";
  }

  @GetMapping(path = "/list")
  public @ResponseBody Iterable<DBConnection> getAllConnections() {
    return connectionRep.findAll();

  }

  @GetMapping(path = "/schema")
  public @ResponseBody Iterator<String> getSchema() throws SQLException {
    List<String> retSchemas = new ArrayList<String>();
    try (Connection con = dataSource.getConnection()) {
      DatabaseMetaData meta = con.getMetaData();
      ResultSet schemas = meta.getSchemas();
      while (schemas.next()) {
        retSchemas.add(schemas.getString(Constants.getSchemaName()));
      }
    }
    return retSchemas.iterator();
  }

  @GetMapping(path = "/tables")
  public @ResponseBody Iterator<String> getAllTables() throws SQLException {
    // StringBuffer buff = new StringBuffer();
    List<String> list = new ArrayList<String>();
    try (Connection con = dataSource.getConnection()) {
      DatabaseMetaData meta = con.getMetaData();
      ResultSet tables = meta.getTables(null, null, null, Constants.getColumnNameArray());
      while (tables.next()) {
        list.add(tables.getString(Constants.getTableName()));
      }
    }
    return list.iterator();

  }

  @GetMapping(path = "/columns")
  public @ResponseBody Iterator<DBSchema> getAllComuns() throws SQLException {
    List<DBSchema> retBuff = new ArrayList<DBSchema>();
    try (Connection con = dataSource.getConnection()) {
      DatabaseMetaData meta = con.getMetaData();
      ResultSet tables = meta.getTables(null, null, null, Constants.getColumnNameArray());
      while (tables.next()) {
        DBSchema ent = new DBSchema();
        String table = tables.getString(Constants.getTableName());
        ent.setTable(table);
        ResultSet columns = meta.getColumns(null, null, table, "%");
        while (columns.next()) {
          ent.appendColumn(columns.getString(Constants.getColumnName()));
        }
        retBuff.add(ent);
      }
    }
    return retBuff.iterator();
  }

  @GetMapping(path="/statistics")
  public @ResponseBody Iterator<Statistics> getStatistics() throws SQLException {
    // StringBuffer retBuff = new StringBuffer();
    List<Statistics> retBuff = new ArrayList<Statistics>();
    try (Connection con = dataSource.getConnection();
        Statement statement = con.createStatement()
        ) {
          DatabaseMetaData meta = con.getMetaData();
          ResultSet tables = meta.getTables(null, null, null, Constants.getColumnNameArray());
          // Prolez tabulky
          while(tables.next()) {
            Statistics stats = new Statistics();
            String table = tables.getString(Constants.getTableName());
            stats.setTable(table);
            ResultSet columns = meta.getColumns(null, null, table, "%");
            // prolez sloupce
            while(columns.next()) {
              String column = columns.getString(Constants.getColumnName());
              stats.setColumn(column);
              // Cela tahle sarada by se dala zabalit do nejakeho objektu, ktera ma na starost vse kolem 
              // pripojeni, fetch, odpojeni etc...
              // podobne i se schematy vyse
              statement.execute(String.format(Constants.getMaxTemplate(), column, table));
              ResultSet res = statement.getResultSet();
              // nebude fungovat vsude, asi spravnejsi je si vzit Object a pokusit se to nacastovat nebo jeste lip
              // odnekud brat info o datovem typu -> reflexe
              if(res.next())
                stats.setMax(res.getString("max"));
              res.close();

              statement.execute(String.format(Constants.getMinTeplate(), column, table));
              res = statement.getResultSet();
              if(res.next())
                stats.setMin(res.getString("min"));
              res.close();

              statement.execute(String.format(Constants.getAvgTemplate(), column, table));
              res = statement.getResultSet();
              if(res.next())
                stats.setAvg(res.getString("avg"));
              res.close();

              retBuff.add(stats);
            }
          }    
    }
    return retBuff.iterator();
  }

  @GetMapping(path="/tableStatistics")
  public @ResponseBody Iterator<Statistics> getTableStatistics() throws SQLException {
      List<Statistics> retBuff = new ArrayList<Statistics>();
      try(Connection con = dataSource.getConnection();
          Statement statement = con.createStatement()) {
        DatabaseMetaData meta = con.getMetaData();
        ResultSet tables = meta.getTables(null, null, null, Constants.getColumnNameArray());
        while(tables.next()) {
          Statistics stats = new Statistics();
          String table = tables.getString(Constants.getTableName());
          stats.setTable(table);
          //rows
          statement.executeQuery(String.format(Constants.getRowNums(), table));
          ResultSet cnt = statement.getResultSet();
          if(cnt.next())
            stats.setNumRecords(cnt.getInt("cnt"));
          cnt.close();
          // attrs
          statement.execute(String.format(Constants.getColNums(), table));
          cnt = statement.getResultSet();
          Integer colNum = cnt.getMetaData().getColumnCount();
          stats.setColNum(colNum);
          cnt.close();


          retBuff.add(stats);
        }

      }
      return retBuff.iterator();
  }

}
