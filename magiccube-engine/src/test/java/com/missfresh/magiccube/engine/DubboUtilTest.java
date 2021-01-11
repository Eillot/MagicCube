package com.simon.magiccube.engine;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.engine.util.DubboUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 2020/4/16.
 */
public class DubboUtilTest {

    @Test
    public void dubboExcuteFormTest(){
        //b22
        // 连接注册中心配置tring,10.1.122.73:2181
        //10.2.4.14:32148,30022,31157
        //IP：10.1.122.73,端口：49568
        //b25
        //zk:10.2.4.14:31870,10.2.4.14:30747,10.2.4.14:32720

        String applicationName = "marketing-center-test";
        String zkVersion = "1.0.0";
        //warehouse, materialId
        DubboUtil init = new DubboUtil();

        //测试在途库存
        String zkAddress = "10.2.4.14:31157";
        String [] a = new String[]{"java.lang.String","java.lang.String"};
        String [] param = {"MRYXBJS","1000005"};
        String interfaceName = "com.mryx.atp.service.api.realinternalinv.OnRoadInvService";
        String method = "getOnRoadInvNum";
        Object result = init.excute(interfaceName,method,zkAddress,zkVersion,null, a,param,applicationName );

        String ss = JSONObject.toJSONString(result);
        System.out.print("====================================在途库存：");
        System.out.print(ss);
        System.out.print("**************************************");

        //测试查询可用库存
//        String zkAddress = "10.2.4.14:31157";
//        String interfaceName1 = "com.mryx.atp.service.api.realinternalinv.RealInternalInvService";
//        String method1 = "realInternalInv";
//        Object result1 = init.excute(interfaceName1,method1,zkAddress,zkVersion, a,param,applicationName );
//        String ss1 = JSONObject.toJSONString(result1);
//        System.out.print("====================================真实库存：");
//        System.out.print(ss1);
//        System.out.print("**************************************");
    }

    @Test
    public void dubboExcuteDtoTest(){

        DubboUtil init = new DubboUtil();
        //查询卡上限
        String applicationName = "marketing-center-test";
        String zkVersion = "1.0.0";
        String zkAddress = "10.2.4.14:32720";
        String json = "{\"warehouseGroup\":\"MRYXBJS\",\"warehouseCode\":\"MRYXBJS-ANLILU\",\"materialsId\":\"224098\",\"skuId\":\"224098\",\"bizSource\":\"0\",\"pageNo\":\"1\",\"createDate\":\"2020-04-13\"}";
        String interfaceName1 = "com.mryx.psmbackend.service.api.upperLimit.IPsmUpperLimitRecordService";
        String method1 = "findHistoryUpperLimit";
        Object result1 = init.excute(interfaceName1,method1,zkAddress,zkVersion,null,new String[]{"com.mryx.psmbackend.dto.replenishment.upperLimit.PsmUpperLimitRequestDTO"},new Object[]{JSON.parse(json)},applicationName );
       String ss1 = JSONObject.toJSONString(result1);
        System.out.print("====================================上限：");
        System.out.print(ss1);
        System.out.print("**************************************");
    }

    @Test
    public void dubboExcuteMapTest(){
        DubboUtil init = new DubboUtil();
        //查询卡上限
        String applicationName = "marketing-center-test";
        String zkAddress = "10.2.4.14:32720";
        String zkVersion = "1.0.0";

        //直接将参数封装到map中
        Map<String,String> params = new HashMap<>();
        params.put("warehouseGroup","MRYXBJS");
        params.put("warehouseCode","MRYXBJS-ANLILU");
        params.put("materialsId","224098");
        params.put("skuId","224098");
        params.put("bizSource","0");
        params.put("pageNo","1");
        params.put("createDate","2020-04-13");
        params.put("class","com.mryx.psmbackend.dto.replenishment.upperLimit.PsmUpperLimitRequestDTO");
        String interfaceName1 = "com.mryx.psmbackend.service.api.upperLimit.IPsmUpperLimitRecordService";
        String method1 = "findHistoryUpperLimit";
        Object result1 = init.excute(interfaceName1,method1,zkAddress,zkVersion,null,new String[]{"com.mryx.psmbackend.dto.replenishment.upperLimit.PsmUpperLimitRequestDTO"} ,new Object[]{params},applicationName );
        String ss1 = JSONObject.toJSONString(result1);
        System.out.print("====================================上限：");
        System.out.print(ss1);
        System.out.print("**************************************");

        //json字符串转化成map，并加入参数类型
//        String json = "{\"warehouseGroup\":\"MRYXBJS\",\"warehouseCode\":\"MRYXBJS-ANLILU\",\"materialsId\":\"224098\",\"skuId\":\"224098\",\"bizSource\":\"0\",\"pageNo\":\"1\",\"createDate\":\"2020-04-13\"}";
//        Map maps = (Map) JSON.parse(json);
//        maps.put("class","com.mryx.psmbackend.dto.replenishment.upperLimit.PsmUpperLimitRequestDTO");
//        String interfaceName1 = "com.mryx.psmbackend.service.api.upperLimit.IPsmUpperLimitRecordService";
//        String method1 = "findHistoryUpperLimit";
//        Object result1 = init.excuteDto(interfaceName1,method1,zkAddress,zkVersion,"com.mryx.psmbackend.dto.replenishment.upperLimit.PsmUpperLimitRequestDTO",json,applicationName );
//        String ss1 = JSONObject.toJSONString(result1);
//        System.out.print("====================================上限：");
//        System.out.print(ss1);
//        System.out.print("**************************************");

    }

    @Test
    public void dubboExcuteWithoutParamTypes(){
        DubboUtil init = new DubboUtil();
        //查询卡上限
        String applicationName = "marketing-center-test";
        String zkAddress = "10.2.4.14:32720";
        String zkVersion = "1.0.0";

        //直接将参数封装到map中
        Map<String,String> params = new HashMap<>();
        params.put("warehouseGroup","MRYXBJS");
        params.put("warehouseCode","MRYXBJS-ANLILU");
        params.put("materialsId","224098");
        params.put("skuId","224098");
        params.put("bizSource","0");
        params.put("pageNo","1");
        params.put("createDate","2020-04-13");
        params.put("class","com.mryx.psmbackend.dto.replenishment.upperLimit.PsmUpperLimitRequestDTO");
        String interfaceName1 = "com.mryx.psmbackend.service.api.upperLimit.IPsmUpperLimitRecordService";
        String method1 = "findHistoryUpperLimit";
        Object result1 = init.excute(interfaceName1,method1,zkAddress,zkVersion,null ,null,new Object[]{params},applicationName );
        String ss1 = JSONObject.toJSONString(result1);
        System.out.print("====================================上限：");
        System.out.print(ss1);
        System.out.print("**************************************");
    }

    @Test
    public void dubboExcuteWithoutParamTypes2(){
        DubboUtil init = new DubboUtil();
        //查询卡上限
        String applicationName = "marketing-center-test";
        String zkVersion = "1.0.0";
        String zkAddress = "10.2.4.14:32720";
        String json = "{\"warehouseGroup\":\"MRYXBJS\",\"warehouseCode\":\"MRYXBJS-ANLILU\",\"materialsId\":\"224098\",\"skuId\":\"224098\",\"bizSource\":\"0\",\"pageNo\":\"1\",\"createDate\":\"2020-04-13\",\"class\":\"com.mryx.psmbackend.dto.replenishment.upperLimit.PsmUpperLimitRequestDTO\"}";
        String interfaceName1 = "com.mryx.psmbackend.service.api.upperLimit.IPsmUpperLimitRecordService";
        String method1 = "findHistoryUpperLimit";
        Object result1 = init.excute(interfaceName1,method1,zkAddress,zkVersion,null,null,new Object[]{JSON.parse(json)},applicationName );
        String ss1 = JSONObject.toJSONString(result1);
        System.out.print("====================================上限：");
        System.out.print(ss1);
        System.out.print("**************************************");

    }

    @Test
    public void dubboExcuteMultiParamTypes(){
        DubboUtil init = new DubboUtil();
        //查询卡上限
        String applicationName = "marketing-center-test";
        String zkVersion = "1.0.0";
        String zkAddress = "10.2.4.14:32720";
        String[] types = new String[]{"com.mryx.psmbackend.dto.base.PsmWarehouseCategorySales","java.lang.String"};
        String str = "{\"category\":\"水果\",\"avgSales\":\"100\",\"avgOrderNum\":\"4\",\"orderNum\":\"4\",\"warehouseList\":[\"MRYXBJS_JIUXIANQIAO\",\"MRYXBJS_ANLILU\"]}";

        Object b  = JSON.parse(str);
        Object[] a = new Object[] {b,"1000005"};
        String interfaceName1 = "com.mryx.psmbackend.service.api.psm.PsmWarehouseCategorySalesService";
        String method1 = "getCategorySalesByWarehouseList";
        Object result1 = init.excute(interfaceName1,method1,zkAddress,null,zkVersion,types,a,applicationName );
        String ss1 = JSONObject.toJSONString(result1);
        System.out.print("====================================上限：");
        System.out.print(ss1);
        System.out.print("**************************************");

    }

}
