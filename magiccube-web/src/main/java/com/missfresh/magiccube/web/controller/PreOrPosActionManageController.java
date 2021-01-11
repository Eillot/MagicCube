package com.simon.magiccube.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.simon.magiccube.dao.domain.CaseManage;
import com.simon.magiccube.dao.domain.PreOrPosAction;
import com.simon.magiccube.engine.ICaseManageEngine;
import com.simon.magiccube.engine.IPreOrPosActionEngine;
import com.simon.magiccube.engine.IProductManageEngine;
import com.simon.magiccube.engine.SQLService;
import com.simon.magiccube.engine.common.ResourceManagement;
import com.simon.magiccube.engine.util.HttpClientUtil;
import com.simon.magiccube.facade.support.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/1/13 2:45 下午
 */

@RestController
@CrossOrigin
@RequestMapping("/preorpos/manage")
public class PreOrPosActionManageController {

    private static final Logger logger = LoggerFactory.getLogger(PreOrPosActionManageController.class);

    @Autowired
    private IPreOrPosActionEngine iPreOrPosActionEngine;

    @Autowired
    private IProductManageEngine iProductManageEngine;

    @Autowired
    private ICaseManageEngine iCaseManageEngine;

    @Autowired
    private SQLService sqlService;


    /**
     * 前后置列表
     * @return
     */
    @RequestMapping(value = "/all")
    public CommonResult preorposList(@RequestBody() PreOrPosAction preOrPosAction){
        CommonResult commonResult = new CommonResult();
        preOrPosAction.setActionType(preOrPosAction.getActionType());
        preOrPosAction.setIsDelete(1);
        List<PreOrPosAction> preOrPosActionList = iPreOrPosActionEngine.PreOrPosActionQueryAll(preOrPosAction);
        logger.info("前后置列表查询结果：" + preOrPosActionList);

        HashMap hashMap = new HashMap();
        hashMap.put("list", preOrPosActionList);
        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(hashMap);
        commonResult.setStatus("200");
        return commonResult;
    }

    /**
     * 前后置详情
     * @param preOrPosId
     * @return
     */
    @GetMapping(value = "/details")
    public CommonResult preorposDetails(@RequestParam  @Valid String preOrPosId){
        CommonResult commonResult = new CommonResult();

        //根据前后置id查询前后置数据
        List preOrPoslist = new ArrayList();
        PreOrPosAction preOrPosActionDetails = iPreOrPosActionEngine.PreOrPosActionQueryById(Integer.valueOf(preOrPosId));
        preOrPoslist.add(preOrPosActionDetails);
        logger.info("根据id查询前后置数据：" +preOrPoslist);

        //根据id查询查询业务线
        String productId = preOrPosActionDetails.getProductId();
        JSONObject productList = JSONObject.parseObject(ResourceManagement.queryResBusinessInfoById(productId));
        logger.info("根据业务线id查询业务线数据：" + productList);

        //如果actionType=1，根据databaseId查询数据库信息
        JSONObject dataList = new JSONObject();
        if(preOrPosActionDetails.getActionType()==1){
            dataList = JSONObject.parseObject(ResourceManagement.queryDatabaseDetail(preOrPosActionDetails.getDatabaseId()));
            logger.info("根据数据库id查询数据库数据：" + dataList);

        }

        //如果actionType=2，取action_content内容和request_type内容---前端直接取

        //如果actionType=3，根据action_content中的用例id查询用例表数据
        List<CaseManage> caseManageList = new ArrayList<>();
        if(preOrPosActionDetails.getActionType() == 3){
            String caseId = preOrPosActionDetails.getActionContent();
            caseManageList = iCaseManageEngine.selectCaseById(Integer.valueOf(caseId));
            logger.info("根据用例id查询用例数据：" + caseManageList);
        }

        //如果actionType=4，取action_content内容---前端直接取

        //如果actionType=5，取action_content内容---前端直接取

        HashMap hashMap = new HashMap();
        hashMap.put("preOrPoslist", preOrPoslist);
        hashMap.put("productList",productList);
        hashMap.put("dataList",dataList);
        hashMap.put("caseManageList",caseManageList);
        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(hashMap);
        commonResult.setStatus("200");
        logger.info("前后置详情："+commonResult);

        return commonResult;
    }

    /**
     * 前后置新增页面
     * @return
     */
    @RequestMapping(value = "/inster", method = RequestMethod.POST)
    public CommonResult preorposInster(){
        CommonResult commonResult = new CommonResult();
        //查询业务线
        JSONObject productList = JSONObject.parseObject(ResourceManagement.queryListResBusinessInfo());
        logger.info("查询业务线列表：" + productList);

        //查询数据库
        JSONObject dataList = JSONObject.parseObject(ResourceManagement.queryDatabaseList());
        logger.info("查询数据库列表：" + dataList);

        HashMap hashMap = new HashMap();
        hashMap.put("productList",productList);
        hashMap.put("dataList",dataList);
        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(hashMap);
        commonResult.setStatus("200");
        logger.info("前后置新增页面显示："+commonResult);

        return commonResult;
    }

    /**
     * 查询用例
     * @param caseName
     * @return
     */
    @GetMapping(value = "/caseListQuery")
    public CommonResult caseListQuery(@RequestParam(required = false)String caseName,@RequestParam(required = false)String caseId){
        CommonResult commonResult = new CommonResult();
        HashMap hashMap = new HashMap();

        //如果有caseName，则按caseName模糊查询
        if(null == caseId || "".equals(caseId)){
            List<CaseManage> caseManageList = iCaseManageEngine.caseQueryByCaseName(caseName) ;
            logger.info("返回用例列表：" + caseManageList);
            hashMap.put("caseManageList",caseManageList);
        }else {
            List caseManageList = iCaseManageEngine.selectCaseById(Integer.valueOf(caseId));
            logger.info("返回用例列表：" + caseManageList);
            hashMap.put("caseManageList",caseManageList);
        }


        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(hashMap);
        commonResult.setStatus("200");
        logger.info("前后置新增页面显示："+commonResult);

        return commonResult;
    }

    /**
     * 前后置保存
     * @param params
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public CommonResult preorposSave(@RequestBody() Map<String, Object> params){
        CommonResult commonResult = new CommonResult();

        logger.info("入参 params: {}", params);
        JSONObject jsonParams = new JSONObject(params);

        PreOrPosAction preOrPosAction = new PreOrPosAction();
        preOrPosAction.setActionName(jsonParams.getString("actionName"));

        if(null != jsonParams.getString("remark")){
            preOrPosAction.setRemark(jsonParams.getString("remark"));
        }

        if(null != jsonParams.getString("actionType")){
            preOrPosAction.setActionType(Integer.valueOf(jsonParams.getString("actionType")));
        }

        JSONObject requesterBateBaseJson = jsonParams.getJSONObject("requesterDataBase");
        if(null != requesterBateBaseJson){
            preOrPosAction.setDatabaseId(String.valueOf(requesterBateBaseJson.get("id")));
        }

        if(null != jsonParams.getString("requesterReq")){
            preOrPosAction.setRequestType(jsonParams.getString("requesterReq"));
        }

        if(null != jsonParams.getString("actionContent")){
            preOrPosAction.setActionContent(jsonParams.getString("actionContent"));
        }

        preOrPosAction.setProductId(jsonParams.getString("productId"));
        preOrPosAction.setCreaterName("admin");
        preOrPosAction.setCreaterTime(new Date());
        preOrPosAction.setIsDelete(1);
        int preorposInsert = iPreOrPosActionEngine.PreOrPosActionInsert(preOrPosAction);
        logger.info("前后置新增结果："+preOrPosAction);

        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(preorposInsert);
        commonResult.setStatus("200");

        return commonResult;
    }

    /**
     * 前后置更新
     * @param params
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public CommonResult preorposUpdate(@RequestBody() Map<String, Object> params){
        CommonResult commonResult = new CommonResult();
        PreOrPosAction preOrPosAction = new PreOrPosAction();

        logger.info("入参 params: {}", params);
        JSONObject jsonParams = new JSONObject(params);
        preOrPosAction.setId(jsonParams.getInteger("id"));

        preOrPosAction.setActionName(jsonParams.getString("actionName"));
        if(null != jsonParams.getString("remark")){
            preOrPosAction.setRemark(jsonParams.getString("remark"));
        }
        preOrPosAction.setActionType(Integer.valueOf(jsonParams.getString("actionType")));
        if(null != jsonParams.getString("requesterReq")){
            preOrPosAction.setRequestType(jsonParams.getString("requesterReq"));
        }
        preOrPosAction.setActionContent(jsonParams.getString("actionContent"));
        preOrPosAction.setProductId(jsonParams.getString("productName"));
        preOrPosAction.setModifyName("admin");
        preOrPosAction.setModifyTime(new Date());
        preOrPosAction.setIsDelete(1);
        int preOrPosActionUpdate = iPreOrPosActionEngine.PreOrPosActionUpdate(preOrPosAction);
        logger.info("前后置更新结果："+preOrPosAction);

        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(preOrPosActionUpdate);
        commonResult.setStatus("200");
        return commonResult;
    }

    /**
     * 前后置删除
     * @param preOrPosAction
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public CommonResult preOrPosActionDelete(@RequestBody() PreOrPosAction preOrPosAction){
        CommonResult commonResult = new CommonResult();
        int preOrPosActionDelete = iPreOrPosActionEngine.PreOrPosActionDeleteToIsDeleteById(preOrPosAction);
        logger.info("前后置删除结果："+preOrPosAction);

        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(preOrPosActionDelete);
        commonResult.setStatus("200");
        return commonResult;
    }

    /**
     * 前后置sql测试
     * @param params
     * @return
     */
    @RequestMapping(value = "/sqlTest",method = RequestMethod.POST)
    public CommonResult preorposSQLTest(@RequestBody() Map<String, Object> params){
        CommonResult commonResult = new CommonResult();
        logger.info("入参 params: {}", params);
        JSONObject jsonParams = new JSONObject(params);

        JSONObject requesterBateBaseJson = jsonParams.getJSONObject("requesterDataBase");

        String databaseId = null;
        if(null != requesterBateBaseJson){
            databaseId = String.valueOf(requesterBateBaseJson.get("id"));
        }

        String sqlParam = jsonParams.getString("actionContentSql");
        JSONObject sqlTestResult = JSONObject.parseObject(sqlService.sqlFunction(databaseId,sqlParam));
        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(sqlTestResult);
        commonResult.setStatus("200");
        return commonResult;
    }

    /**
     * 前后置请求测试
     * @param params
     * @return
     */
    @RequestMapping(value = "/requestTest",method = RequestMethod.POST)
    public CommonResult preorposRequestTest(@RequestBody() Map<String, Object> params){
        CommonResult commonResult = new CommonResult();
        logger.info("入参 params: {}", params);
        JSONObject jsonParams = new JSONObject(params);
        JSONObject requestTestResult = new JSONObject();

        //请求url
        String url =jsonParams.getString("actionContentReq");

        String requestType = jsonParams.getString("requesterReq");
        //请求类型为1，走post
        if(requestType.equals("1")){
            JSONObject paramsJson = new JSONObject();
            if(jsonParams.getJSONObject("remark") == null){

            }else {
                paramsJson = jsonParams.getJSONObject("remark");
            }
            try {
                String resultString = HttpClientUtil.sendPostRequest(url, String.valueOf(paramsJson));
                requestTestResult.put("requestTestResult",resultString);
                logger.info("post请求结果："+resultString);
            } catch (HttpClientUtil.HttpUtilException e) {
                e.printStackTrace();
                logger.info("post请求结果：失败"+e);
                requestTestResult.put("requestTestResult",e);
            }
        }

        //请求类型为2，走get
        if(requestType.equals("2")){
            //get请求，拆分成url和入参
            String[] getUrlAll = url.split("\\?");
            String getUrl = getUrlAll[0];
            String getParamsAll = getUrlAll[1];
            //有多个入参时拆分入参
            String[] getParams = getParamsAll.split("&");
            //遍历入参
            Map paramsMap = new HashMap();
            for(int i=0;i<getParams.length;i++){
                String paramsCF = getParams[i];
                String[] param = paramsCF.split("=");
                paramsMap.put(param[0],param[1]);
            }
            try {
                String resultString = HttpClientUtil.sendGetRequest(getUrl, paramsMap);
                logger.info("get请求结果：" +resultString);
                requestTestResult.put("requestTestResult",resultString);
            } catch (HttpClientUtil.HttpUtilException e) {
                e.printStackTrace();
                logger.info("get请求结果：失败"+e);
                requestTestResult.put("requestTestResult",e);
            }
        }

        commonResult.setCode("0");
        commonResult.setMsg("success");
        commonResult.setData(requestTestResult);
        commonResult.setStatus("200");
        return commonResult;
    }

}
