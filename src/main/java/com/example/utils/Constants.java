package com.example.utils;

public final class Constants {

  private static String[] columnNameArray = {"TABLE"};

  private static String tableName = "TABLE_NAME";

  private static String columnName = "COLUMN_NAME";

  private static String schemaName = "TABLE_SCHEM";

  private static String maxTemplate = "SELECT MAX(%s) as max FROM %s ;";

  private static String minTemplate = "SELECT MIN(%s) as min FROM %s ;";

  private static String avgTemplate = "SELECT AVG(%s) as avg FROM %s ;";

  private Constants() {
  }

  public static String getSchemaName() {
    return schemaName;
  }

  public static String getColumnName() {
    return columnName;
  }

  public static String getTableName() {
    return tableName;
  }

  public static String[] getColumnNameArray() {
    return columnNameArray;
  }

  public static String getMaxTemplate() {
    return maxTemplate;
  }

  public static String getMinTeplate() { 
    return minTemplate;
  }

  public static String getAvgTemplate() {
    return avgTemplate;
  }
  
}
