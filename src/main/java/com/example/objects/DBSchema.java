package com.example.objects;

import java.util.*;

public class DBSchema {

  private String table;

  private List<String> columns;

  public DBSchema() {
    this.table = null;
    this.setColumns(new ArrayList<String>());
  }

  public List<String> getColumns() {
    return this.columns;
  }

  public void setColumns(List<String> columns) {
    this.columns = columns;
  }

  public String getTable() {
    return this.table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  public void appendColumn(String column) {
    this.columns.add(column);
  }

  
}
