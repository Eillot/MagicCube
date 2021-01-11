package com.simon.magiccube.engine.impl;

import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.simon.magiccube.engine.SQLService;
import com.simon.magiccube.engine.common.ResourceManagement;
import com.simon.magiccube.engine.common.database.DBUtil;
import com.simon.magiccube.engine.util.ExcpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/4/9 2:14 下午
 */
@Service
public class SQLServiceImp implements SQLService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String sqlFunction(String databaseId, String sqlParam) {
        JSONObject result = new JSONObject();

        //连接资源管理，根据数据库id查询数据库内容
        JSONObject databaseDetail = JSONObject.parseObject(ResourceManagement.queryDatabaseDetail(databaseId));
        String ip = String.valueOf(databaseDetail.getJSONObject("result").get("dbIp"));
        int port = Integer.parseInt(String.valueOf(databaseDetail.getJSONObject("result").get("dbPort")));
        String username = String.valueOf(databaseDetail.getJSONObject("result").get("userName"));
        String pwd = String.valueOf(databaseDetail.getJSONObject("result").get("pwd"));
        String dataBase = String.valueOf(databaseDetail.getJSONObject("result").get("dataBase"));

        //连接db资源池
        DBUtil db = null;
        if (db == null) {
            db = DBUtil.pool(ip, String.valueOf(port), username, pwd, dataBase);
            try {
                db.connection();
                result.put("DBConnectionResult","DB连接成功");
                logger.info("DB连接成功--" + "数据库名称：" + dataBase + "，数据库ip：" + ip);
            } catch (Exception e) {
                e.printStackTrace();
                String eStr = ExcpUtil.buildErrorMessage(e);
                result.put("DBConnectionResult","DB连接异常"+eStr);
                logger.info("DB连接异常：" + eStr);
            }
        }

        try {

            //拆分sql语句，判断sql语句类型
            String[] sqlParamArr = sqlParam.split(" ");
            String sqlType = sqlParamArr[0];
            System.out.println("@@@:" + sqlType);
            //判断如果sqlType=select，则走查询
            if (sqlType.equalsIgnoreCase("select")) {
                logger.info("操作类型为：SELECT");
                logger.info("操作语句为：" + sqlParam);

                if (sqlParam.contains("where") || sqlParam.contains("WHERE")) {
                    List<Map<String, Object>> resultMapList = db.queryForMapList(sqlParam);
                    result.put("SQLTestResult", "SQL查询成功："+resultMapList);
                    logger.info("SQL执行查询结果：" + resultMapList.get(0));

                } else {
                    result.put("SQLTestResult", "查询语句需添加where条件！");
                    logger.info("查询语句需添加where条件！");

                }
            }

            //判断如果sqlType=INSERT，则走插入
            if (sqlType.equalsIgnoreCase("insert")) {
                logger.info("操作类型为：INSERT");
                logger.info("操作语句为：" + sqlParam);

                int up = db.update(sqlParam);
                if(up == 1){
                    result.put("SQLTestResult：","SQL插入成功");
                    logger.info("SQL执行插入结果：" + up);
                }else {
                    result.put("SQLTestResult：","SQL插入失败");
                    logger.info("SQL执行插入结果：" + up);
                }

            }

            //判断如果sqlType=update，则走更新
            if(sqlType.equalsIgnoreCase("update")){
                logger.info("操作类型为：UPDATE");
                logger.info("操作语句为：" + sqlParam);

                int up = db.update(sqlParam);
                if(up == 1){
                    result.put("SQLTestResult","SQL更新成功");
                    logger.info("SQL执行更新结果：" + up);
                }else {
                    result.put("SQLTestResult","QL更新失败");
                    logger.info("SQL执行更新结果：" + up);
                }
            }

            if (db != null) {
                db.closeConnection();
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            String eStr = ExcpUtil.buildErrorMessage(e);
            logger.info("SQL执行异常："+eStr);
            result.put("SQLTestResult","SQL执行异常："+eStr);
            db.closeConnection();
        }

        logger.info("sql操作结果：" + result.toJSONString());

        return result.toJSONString();
    }


    @Override
    public Object sqlByParam(String addressId, String dbSentance, String access) {
        JSONObject result = new JSONObject();

        Object obj = null;
        //连接资源管理，根据数据库id查询数据库内容
        JSONObject databaseDetail = JSONObject.parseObject(ResourceManagement.queryDatabaseDetail(addressId));
        String ip = String.valueOf(databaseDetail.getJSONObject("result").get("dbIp"));
        int port = Integer.parseInt(String.valueOf(databaseDetail.getJSONObject("result").get("dbPort")));
        String username = String.valueOf(databaseDetail.getJSONObject("result").get("userName"));
        String pwd = String.valueOf(databaseDetail.getJSONObject("result").get("pwd"));
        String data_base = String.valueOf(databaseDetail.getJSONObject("result").get("dataBase"));

        DBUtil db = null;
        if (db == null){
            db = DBUtil.pool(ip,String.valueOf( port ),username,pwd,data_base);
            try {
                db.connection();
            } catch (Exception e) {
                e.printStackTrace();
                String eStr= ExcpUtil.buildErrorMessage(e);
                logger.error("DB连接异常："+eStr);
            }
        }
        try {
            logger.info("将要执行的SQL语句为:dbSentance={}",dbSentance);

            if (dbSentance.contains("select")||dbSentance.contains("SELECT")){
                if (dbSentance.contains("where")||dbSentance.contains("WHERE")){
                    List<Map<String, Object>> resultMapList=db.queryForMapList(dbSentance);
                    logger.info("sql执行结果为:result={}",resultMapList);
                    result.put("data",resultMapList);

//                    TODO jsonpath获取
                    obj= JsonPath.read(result,access);

                }else {
                    logger.error("参数化获取SQL操作--查询语句需添加where条件！");
                    return "参数化获取SQL操作--查询语句需添加where条件！";
                }
            }else {
                logger.error("参数化获取SQL操作--仅支持查询操作！");
                return "参数化获取SQL操作--仅支持查询操作！";
            }
            if (db != null) {
                db.closeConnection();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            db.closeConnection();
        }
        return obj;
    }
}
