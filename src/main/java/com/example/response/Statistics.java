package com.example.response;

public class Statistics {

  private String table;

  private Integer numRecords;

  private Integer colNum;

  private String[] attributes;

  private String column;

  private String max;

  private String min;

  private String avg;

  public Statistics() {

    this.column = null;
    this.table = null;

  }

  public Integer getColNum() {
    return colNum;
  }

  public void setColNum(Integer colNum) {
    this.colNum = colNum;
  }

  public String[] getAttributes() {
    return attributes;
  }

  public void setAttributes(String[] attributes) {
    this.attributes = attributes;
  }

  public Integer getNumRecords() {
    return numRecords;
  }

  public void setNumRecords(Integer numRecords) {
    this.numRecords = numRecords;
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
