package com.simon.magiccube.engine.common.database;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/4/9 11:12 上午
 * 数据库类型
 */
public enum DBType {


    MSSQL2000("com.microsoft.jdbc.sqlserver.SQLServerDriver"), MSSQL2005(
            "com.microsoft.sqlserver.jdbc.SQLServerDriver"), MSSQL2008(
            "com.microsoft.sqlserver.jdbc.SQLServerDriver"), MSSQL(
            DBType.MSSQL2005.getDBType()),

    MYSQL3("com.mysql.jdbc.Driver"), MYSQL4("com.mysql.jdbc.Driver"), MYSQL5(
            "com.mysql.jdbc.Driver"), MYSQL(DBType.MYSQL5.getDBType()),

    ORCALE8("oracle.jdbc.driver.OracleDriver"), ORCALE9(
            "oracle.jdbc.driver.OracleDriver"), ORCALE10(
            "oracle.jdbc.driver.OracleDriver"), ORCALE11(
            "oracle.jdbc.driver.OracleDriver"), ORCALE(DBType.ORCALE11
            .getDBType());

    private String db;

    private DBType(String u) {
        db = u;
    }

    public String getDBType() {
        return db;
    }
}
