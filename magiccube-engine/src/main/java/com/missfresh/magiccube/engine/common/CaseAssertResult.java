package com.simon.magiccube.engine.common;

import com.alibaba.fastjson.JSON;
import com.simon.magiccube.dao.domain.ReportManage;
import com.simon.magiccube.dao.mapper.ReportManageMapper;
import com.simon.magiccube.engine.impl.ReportManageEngineImp;
import com.simon.magiccube.engine.util.SpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2020/3/26 12:14 PM
 * @File : CaseAssertResult
 * @Software: IntelliJ IDEA 2018.1.8
 */
@Component
public class CaseAssertResult {

    /**
     * 统计断言结果并保存
     */
    @Autowired
    private ReportManageMapper reportManageMapper;
    @Autowired
    private ReportManageEngineImp reportManageEngineImp;
    //private static CaseAssertResult instance;

    private CaseAssertResult (){}

//    /**
//     * 懒汉式-单例模式 线程安全
//     * @return
//     */
//    public static synchronized CaseAssertResult getInstance() {
//        if (instance == null) {
//            instance = new CaseAssertResult();
//        }
//        return instance;
//    }

    /**
     *
     * @param casid
     * @param assertDes 断言名称
     * @param assertResult 断言结果：1 成功 ，2失败，3异常
     * @return
     */
    public int assertResult(String casid, String assertDes, String assertResult){
        List maplist = new ArrayList();
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("assertDes",assertDes);
        stringMap.put("assertResult",assertResult);
        maplist.add(stringMap);
        String result = JSON.toJSONString(maplist);
        ReportManage reportManage= new ReportManage();
        reportManage.setAssertResultAndDes(result);
        reportManage.setCaseId(casid);
        int assresult = reportManageEngineImp.ReportManageUpdateAssertByCaseId(reportManage);
        return assresult;
    }
}
