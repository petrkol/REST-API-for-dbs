package com.example.restservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DBConnection {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

  private String hostName;

  private Integer port;

  private String databaseName;

  private String userName;

  private String password; // by se sikla Base64

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }

  public String getHostName() {
    return this.hostName;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public Integer getPort() {
    return this.port;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }

  public String getDatabaseName() {
    return this.databaseName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return this.password;
  }
}
