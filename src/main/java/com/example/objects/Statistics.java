package com.example.objects;

public class Statistics {

  private String table;

  private String column;

  private String max;

  private String min;

  private String avg;

  public Statistics() {

    this.column = null;
    this.table = null;

  }

  public String getAvg() {
    return avg;
  }

  public void setAvg(String avg) {
    this.avg = avg;
  }

  public String getMin() {
    return min;
  }

  public void setMin(String min) {
    this.min = min;
  }

  public String getMax() {
    return max;
  }

  public void setMax(String max) {
    this.max = max;
  }

  public String getColumn() {
    return column;
  }

  public String getTable() {
    return table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  public void setColumn(String columns) {
    this.column = columns;
  }

  
}
