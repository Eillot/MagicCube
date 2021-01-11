package com.simon.magiccube.engine.common.database;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/4/9 11:15 上午
 * 数据库方法
 */
public class DBUtil {
    public static QueryRunner queryRunner;
    private static Map<String, DBUtil> connectionPool = new HashMap<>();

    static {
        queryRunner = new QueryRunner();
    }

    private Connection conn;
    private DBConnection dbct;
    private ThreadLocal<PreparedStatement> threadLocal = null;
    private ThreadLocal<Short> threadLocal_cnt = null;

    private DBUtil(String dbhost, String dbport, String dbuname, String dbpwd, String dtbase) {

        dbct = new DBConnection(DBType.MYSQL, dbhost, dbuname, dbpwd, dtbase, dbport);
    }

    //链接池模式
    public static synchronized DBUtil pool(String dbhost, String dbport, String dbuname, String dbpwd, String dtbase) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(dbhost)
                .append(dbport)
                .append(dbuname)
                .append(dbpwd)
                .append(dtbase);

        String dbKey = stringBuffer.toString();

        if (connectionPool.get(dbKey) == null) {
            DBUtil dbUtil = new DBUtil(dbhost, dbport, dbuname, dbpwd, dtbase);
            connectionPool.put(dbKey, dbUtil);
            return dbUtil;
        }

        return connectionPool.get(dbKey);
    }

    /**
     * 数据库连接
     *
     * @return
     */
    public void connection() throws Exception {
        Class.forName(dbct.getJDBCDriver()).newInstance();
        conn = DriverManager.getConnection(dbct.getJDBCString(), dbct.getDbusername(), dbct.getDbpasswd());
    }

    /**
     * 关闭Connection
     */
    public void closeConnection() {
        try {
            if (conn != null && conn.isValid(0)) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询一行多列记录，将结果封装成一个Map，key为字段名，value为字段值
     *
     * @param sql
     * @param params
     * @return
     */
    public Map<String, Object> queryForMap(String sql, Object... params) {
        Map<String, Object> result = null;
        try {
            result = queryRunner.query(conn, sql, new MapHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询多行多列记录，将结果列表中的每一行封装成一个Map，key为字段名，value为字段值
     *
     * @param sql
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryForMapList(String sql, Object... params) throws Exception {
        List<Map<String, Object>> result = null;
//        try {
        result = queryRunner.query(conn, sql, new MapListHandler(), params);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return result;
    }

    /**
     * 查询多行一列数据
     *
     * @param <T>
     * @param sql
     * @param params
     * @return
     */
    public <T> List<T> queryForObjectList(String sql, Class<T> c, Object... params) throws SQLException {
        List<T> result = null;
        try {
            result = queryRunner.query(conn, sql, new ColumnListHandler<T>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询单个值
     *
     * @param <T>
     * @param sql
     * @param params
     * @return
     */
    public <T> T queryForObject(String sql, Class<T> c, Object... params) {
        T result = null;
        try {
            result = queryRunner.query(conn, sql, new ScalarHandler<T>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 插入
     *
     * @param sql
     * @param params
     * @return
     */
    public void insert(String sql, Object... params) {
        try {
            queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入(返回主键)
     *
     * @param sql
     * @param params
     * @return
     */
    public Object insertReturnPK(String sql, Object... params) {
        try {
            return queryRunner.insert(conn, sql, new ScalarHandler<Object>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 批量插入
     *
     * @param sql
     * @param params
     * @return
     */
    public Object insertBatch(String sql, Object[][] params) {
        try {
            return queryRunner.batch(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 更新
     *
     * @param sql
     * @param params
     * @return
     */
    public int update(String sql, Object... params) throws SQLException {
//        try {
        return queryRunner.update(conn, sql, params);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return 0;
    }

    /**
     * 批量更新
     *
     * @param sql
     * @param params
     * @return
     */
    public void updateBatch(String sql, Object[][] params) {
        try {
            queryRunner.batch(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭PreparedStatement
     */
    public void closePreparedStatement(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭Connection 和 Statement
     *
     * @param stmt
     */
    public void close(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public PreparedStatement prepareStatement(String sql) {
        try {
            return conn.prepareStatement(sql);
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * 批量操作准备
     *
     * @param sql
     */
    public void batchPrepare(final String sql) {
        if (threadLocal == null) {
            threadLocal = new ThreadLocal<PreparedStatement>() {
                @Override
                protected PreparedStatement initialValue() {
                    try {
                        return conn != null ? conn.prepareStatement(sql) : null;
                    } catch (SQLException e) {
                        return null;
                    }
                }
            };
        }
    }

    /**
     * 设置参数
     *
     * @param params
     */
    public void batchAdd(Object... params) {
        try {
            if (threadLocal != null) {
                PreparedStatement stmt = threadLocal.get();
                for (int i = 0; i < params.length; i++) {
                    if (params[i] != null) {
                        stmt.setObject(i + 1, params[i]);
                    } else {
                        stmt.setNull(i + 1, Types.VARCHAR);
                    }
                }
                stmt.addBatch();

                Short cnt = threadLocal_cnt.get();
                threadLocal_cnt.set(++cnt);
                if (cnt % 100 == 0) {
                    stmt.executeBatch();
                    stmt.clearBatch();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量执行并且关闭连接
     */
    public void batchExecuteAndClose() {
        try {
            if (threadLocal != null) {
                PreparedStatement stmt = threadLocal.get();
                stmt.executeBatch();
                stmt.clearBatch();
                stmt.closeOnCompletion();
                stmt = null;
                threadLocal.remove();
                threadLocal = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
