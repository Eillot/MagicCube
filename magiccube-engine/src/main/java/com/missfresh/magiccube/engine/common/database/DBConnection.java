package com.simon.magiccube.engine.common.database;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/4/9 11:14 上午
 * 数据库连接
 */
public class DBConnection {

    private String dbusername;

    private String dbpasswd;

    private DBType dbtype;

    private String JDBCString;

    public DBConnection(DBType type, String host, String username, String passwd, String base, String port) {
        dbusername = username;
        dbpasswd = passwd;
        switch (type) {
            case MYSQL:
                dbtype = DBType.MYSQL;
            case MYSQL3:
                dbtype = DBType.MYSQL3;
            case MYSQL4:
                dbtype = DBType.MYSQL4;
            case MYSQL5: {
                dbtype = DBType.MYSQL5;
                if (port.isEmpty()) port = "3306";
                JDBCString = String.format("jdbc:mysql://%s:%s/%s?zeroDateTimeBehavior=convertToNull", host, port, base);
                break;
            }
            case ORCALE8:
                dbtype = DBType.ORCALE8;
            case ORCALE9:
                dbtype = DBType.ORCALE9;
            case ORCALE10:
                dbtype = DBType.ORCALE10;
            case ORCALE11:
                dbtype = DBType.ORCALE11;
            case ORCALE: {
                dbtype = DBType.ORCALE;
                if (port.isEmpty()) port = "1521";
                JDBCString = String.format("jdbc:oracle:thin:@%s:%s:%s", host, port, base);
                break;
            }
            case MSSQL2000:
                dbtype = DBType.MSSQL2000;
            case MSSQL2005:
                dbtype = DBType.MSSQL2005;
            case MSSQL2008:
                dbtype = DBType.MSSQL2008;
            case MSSQL: {
                dbtype = DBType.MSSQL;
                if (port.isEmpty()) port = "1433";
                JDBCString = String.format("jdbc:sqlserver://%s:%s;DatabaseName=%s", host, port, base);
                break;
            }
            default:
                break;
        }
    }

    public String getJDBCString() {
        return JDBCString;
    }

    public String getJDBCDriver() {
        return dbtype.getDBType();
    }

    public String getDbusername() {
        return dbusername;
    }

    public String getDbpasswd() {
        return dbpasswd;
    }
}
