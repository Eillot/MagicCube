package com.simon.magiccube.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.simon.magiccube.dao.domain.SmallTool;
import com.simon.magiccube.engine.ISmallToolEngine;
import com.simon.magiccube.engine.common.ResourceManagement;
import com.simon.magiccube.facade.dto.ParameterDTO;
import com.simon.magiccube.facade.dto.SmallToolDTO;
import com.simon.magiccube.facade.support.CommonResult;
import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.service.Imp.DubboServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by wangxl03 on 2020/3/8.
 */
@RestController
@CrossOrigin
@RequestMapping("/smalltool")
public class SmallToolController {

    private static final Logger logger = LoggerFactory.getLogger(SmallToolController.class);

    @Autowired
    private ISmallToolEngine iSmallToolEngine;


    /**
     * 查询小工具列表
     * @param smallTool
     * @return
     */
    @RequestMapping(value = "/querySmallToolList", method = RequestMethod.POST)
    public CommonResult getSmallToolList(@RequestBody() SmallTool smallTool) {
        CommonResult commonResult = new CommonResult();
        smallTool.setIsDelete(1);
        List<SmallTool> smallTools = iSmallToolEngine.SmallToolQueryAll(smallTool);
        logger.info("查询小工具列表返回数据：" + smallTools);

        commonResult.setData(smallTools);
        commonResult.setStatus("200");
        commonResult.setCode("0");
        commonResult.setMsg("查询成功");
        return commonResult;
    }

    /**
     * 通过ID查询小工具
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    public CommonResult getSmallToolById(@RequestParam @Valid Integer id) {
        CommonResult commonResult = new CommonResult();
        logger.info("通过id查询小工具，id = " + id);
        SmallTool smallTool = iSmallToolEngine.SmallToolQueryById(id);
        logger.info("通过id查询小工具,查询结果：" + smallTool);

        commonResult.setMsg("查询成功");
        commonResult.setData(smallTool);
        commonResult.setStatus("200");
        commonResult.setCode("0");
        return commonResult;
    }

    /**
     * 添加小工具
     * @param smallTool
     * @return
     */
    @RequestMapping(value = "/addSmallTool", method = RequestMethod.POST)
    public CommonResult addSmallTool(@RequestBody() SmallTool smallTool) {
        CommonResult commonResult = new CommonResult();
        smallTool.setInterfaceType(smallTool.getInterfaceType());
        smallTool.setInterfaceId(smallTool.getInterfaceId());
        smallTool.setParameter(smallTool.getParameter());
        smallTool.setCreaterName(smallTool.getCreaterName());
        smallTool.setIsDelete(1);
        int record = iSmallToolEngine.SmallToolInsert(smallTool);
        logger.info("小工具添加接口信息：" + smallTool);
        commonResult.setMsg("添加成功");
        commonResult.setStatus("0");
        commonResult.setCode("0");
        return commonResult;
    }

    /**
     * 修改小工具
     * @param smallTool
     * @return
     */
    @RequestMapping(value = "/updateSmallTool", method = RequestMethod.POST)
    public CommonResult updateSmallTool(@RequestBody() SmallTool smallTool) {
        CommonResult commonResult = new CommonResult();
        if(smallTool.getId() == 0){
            logger.error("id不存在，入参id=" + smallTool.getId());
            commonResult.setMsg("ID不存在，修改失败");
            commonResult.setStatus("200");
            commonResult.setCode("-1");
        }else {
            smallTool.setId(smallTool.getId());
            smallTool.setInterfaceType(smallTool.getInterfaceType());
            smallTool.setInterfaceId(smallTool.getInterfaceId());
            smallTool.setParameter(smallTool.getParameter());
            smallTool.setModifyName(smallTool.getModifyName());
            smallTool.setIsDelete(1);
            int record = iSmallToolEngine.SmallToolUpdate(smallTool);
            logger.info("修改小工具接口信息：" + smallTool);
            commonResult.setMsg("修改成功");
            commonResult.setStatus("200");
            commonResult.setCode("0");
        }

        return commonResult;
    }

    /**
     * 通过id删除小工具
     * @param id
     * @return
     */
    @RequestMapping(value = "/delSmallToolById", method = RequestMethod.POST)
    public CommonResult delSmallToolById(@RequestParam @Valid Integer id) {
        CommonResult commonResult = new CommonResult();
        int record = iSmallToolEngine.SmallToolDeleteById(id);
        if(record != 0) {
            logger.info("删除成功，删除条数：" + record);
            commonResult.setStatus("200");
            commonResult.setCode("0");
            commonResult.setMsg("删除成功");
        }else {
            logger.info("删除失败" );
            commonResult.setStatus("200");
            commonResult.setCode("-1");
            commonResult.setMsg("删除条数：" + record);
        }

        return commonResult;
    }

    /**
     * 查询接口列表
     * @param interName
     * @return
     */
    @RequestMapping(value = "/getInterfaceList", method = RequestMethod.POST)
    public CommonResult getInterfaceListByName(@RequestParam(required = false) String interName, @RequestParam(required = false) String type) {
        CommonResult commonResult = new CommonResult();
        JSONObject interList = JSONObject.parseObject(ResourceManagement.queryListResInterfaceInfo(interName,null,type));

        if(interList != null) {
            logger.info("查询到接口列表：" + interList);
            commonResult.setStatus("200");
            commonResult.setCode("0");
            commonResult.setMsg("查询接口列表成功");
            commonResult.setData(interList);
        }else {
            logger.info("查询接口列表失败" );
            commonResult.setStatus("200");
            commonResult.setCode("-1");
            commonResult.setMsg("查询失败");
            commonResult.setData(null);
        }

        return commonResult;
    }

    /**
     * 根据接口id查询接口详情
     * @param interId
     * @return
     */
    @RequestMapping(value = "/getInterfaceDetail", method = RequestMethod.GET)
    public CommonResult getInterfaceById(@RequestParam String interId) {
        CommonResult commonResult = new CommonResult();
        SmallToolDTO smallToolDTO = new SmallToolDTO();
        SmallTool smallTool = new SmallTool();
        smallTool.setInterfaceId(interId);
        smallTool.setIsDelete(1);
        List<SmallTool> smallTools = iSmallToolEngine.SmallToolQueryAll(smallTool);
        if(smallTools != null && smallTools.size()==1){
            smallToolDTO.setId(smallTools.get(0).getId());
            smallToolDTO.setParameter(smallTools.get(0).getParameter());
            smallToolDTO.setCreaterTime(smallTools.get(0).getCreaterTime());
            smallToolDTO.setCreaterName(smallTools.get(0).getCreaterName());
            smallToolDTO.setInterfaceId(smallTools.get(0).getInterfaceId());
            smallToolDTO.setIsDelete(smallTools.get(0).getIsDelete());
            smallToolDTO.setRemark(smallTools.get(0).getRemark());
        }else if(smallTools != null && smallTools.size()>1){
            logger.info("查询接口异常，应该返回一条，实际返回多条"+ smallTools );
            commonResult.setCode("1000");
            commonResult.setMsg("查询数据异常");
            commonResult.setData(null);
        }
        JSONObject interList = JSONObject.parseObject(ResourceManagement.queryListResInterfaceInfo(null,interId,null));
        if(interList != null ){
            JSONArray jsonArray = interList.getJSONArray("result");
            if( jsonArray.size()==1){
                JSONObject interfaceDetail = interList.getJSONArray("result").getJSONObject(0);
                smallToolDTO.setInterfaceId(interfaceDetail.get("id").toString());
                smallToolDTO.setInterName(interfaceDetail.get("interName").toString());
                smallToolDTO.setInterfaceContent(interfaceDetail.get("interUrl").toString());
                smallToolDTO.setMethodName(interfaceDetail.get("methodName").toString());
                smallToolDTO.setMethodDict(interfaceDetail.get("remark").toString());
                logger.info("查询到小工具接口详情：" + smallToolDTO);
                commonResult.setCode("0");
                commonResult.setMsg("查询小工具接口详情成功");
                commonResult.setData(smallToolDTO);
            }else if(jsonArray.size()>1) {
                logger.info("查询接口异常，应该返回一条，实际返回多条" + interList.getJSONArray("result"));
                commonResult.setCode("-1");
                commonResult.setMsg("查询异常");
                commonResult.setData(interList.getJSONArray("result"));
            }
        }else {
            logger.info("查询小工具接口详情失败" );
            commonResult.setCode("-1");
            commonResult.setMsg("查询失败");
            commonResult.setData(null);
        }
        commonResult.setStatus("200");
        return commonResult;
    }

    /**
     * 查询环境列表
     * @param serverName
     * @return
     */
    @RequestMapping(value = "/getServerList", method = RequestMethod.POST)
    public CommonResult getServerList (@RequestParam(required = false) String serverName) {
        CommonResult commonResult = new CommonResult();
        JSONObject interList = JSONObject.parseObject(ResourceManagement.queryListResServerenvInfo(null,serverName));
        if(interList != null) {
            logger.info("查询到环境更列表：" + interList);
            commonResult.setCode("0");
            commonResult.setMsg("查询环境列表成功");
            commonResult.setData(interList);
            System.out.print(interList.toJSONString());
        }else {
            logger.info("查询环境列表失败" );
            commonResult.setCode("-1");
            commonResult.setMsg("查询失败");
            commonResult.setData(null);
        }
        commonResult.setStatus("200");
        return commonResult;
    }

    /**
     * 小工具执行接口调用
     * @param smallToolDTO
     * @return
     */
    @RequestMapping(value = "/excuteSmallTool", method = RequestMethod.POST)
    public CommonResult excuteSmallTool(@RequestBody() SmallToolDTO smallToolDTO) {
        logger.info("调用接口/excuteSmallTool，入参："+smallToolDTO.toString());
        Object response = null;
        CommonResult commonResult = new CommonResult();
        if(null == smallToolDTO.getInterfaceType() || "".equalsIgnoreCase(smallToolDTO.getInterfaceType())) {
            logger.error("接口类型不能为空:interfaceType is null");
            commonResult.setMsg("接口类型不能为空");
        }else if(null == smallToolDTO.getInterfaceContent() || "".equalsIgnoreCase(smallToolDTO.getInterfaceContent())) {
            logger.error("接口地址不能为空:interfaceContent is null or ''");
            commonResult.setMsg("接口地址不能为空");
        }else if("1".equalsIgnoreCase(smallToolDTO.getInterfaceType())) {//1-http接口
            if(null == smallToolDTO.getRunEnv() || "".equalsIgnoreCase(smallToolDTO.getRunEnv())) {
                logger.error("运行环境不能为空:runEnv is null or ''");
                commonResult.setMsg("运行环境不能为空");
            }
            // 调用执行调用http接口
            response = iSmallToolEngine.excuteHttpSmallTool(smallToolDTO.getRequestType(), smallToolDTO.getRunEnv(),
                    smallToolDTO.getInterfaceContent(), smallToolDTO.getParameterString());
            commonResult.setData(response);
            //保存或更新小工具
            handlerSmallToll(smallToolDTO);

            logger.info("调用http接口成功");
            commonResult.setMsg("调用http接口成功");
        }else if("2".equalsIgnoreCase(smallToolDTO.getInterfaceType())) {//2-dubbo接口
            DubboServiceImp dubboServiceImp = new DubboServiceImp();
            // 调用执行调用dubbo接口
            List<ParameterDTO> parameters = smallToolDTO.getParameters();
            String [] parameterTypes;
            Object [] parameterObjs;
            int parameterCount = 0;
            if (parameters !=null) {
                parameterCount = parameters.size();
            }
            //组装接口参数及参数类型
            parameterTypes = new String[parameterCount];
            parameterObjs= new Object[parameterCount];
            for (int i=0; i<parameters.size(); i++) {
                parameterTypes[i] = dubboServiceImp.getParamTypeByCode(parameters.get(i).getParamType());
                parameterObjs[i] = parameters.get(i).getParameter();
            }
            //applicationName为空，则填充默认值test
            if(null == smallToolDTO.getApplicationName()||smallToolDTO.getApplicationName().equalsIgnoreCase("")){
                smallToolDTO.setApplicationName("test");
            }
            //执行buddo接口调用
            response = iSmallToolEngine.excuteDubboSmallTool(smallToolDTO.getInterfaceContent(), smallToolDTO.getMethodName(),
                    smallToolDTO.getRegistry(),smallToolDTO.getZkVersion(), smallToolDTO.getGroup(), parameterTypes,parameterObjs,smallToolDTO.getApplicationName());
            commonResult.setData(response);

            if(null!=response){
                logger.info("接口返回："+response);
            }
            //保存或更新小工具
//            handlerSmallToll(smallToolDTO);
            logger.info("调用dubbo接口成功");
            commonResult.setMsg("调用dubbo接口成功");
        }else {
            logger.error("接口类型错误");
            commonResult.setMsg("接口类型错误");
        }
        commonResult.setCode("0");
        commonResult.setStatus("200");
        return commonResult;
    }

    /**
     * 新增/更新小工具
     * @param smallToolDTO
     */
    public void handlerSmallToll(SmallToolDTO smallToolDTO) {
        SmallTool smallTool = new SmallTool();
        smallTool.setInterfaceId(smallToolDTO.getInterfaceId());
        List<SmallTool> list = iSmallToolEngine.SmallToolQueryAll(smallTool);
        //封装接口参数
        String parameter = "";
        if("1".equalsIgnoreCase(smallToolDTO.getInterfaceType())) {//1-http接口
            parameter = smallToolDTO.getParameterString();
        }else if("2".equalsIgnoreCase(smallToolDTO.getInterfaceType())) {//2-dubbo接口
            parameter = JSONArray.toJSONString(smallToolDTO.getParameters());
        }
        //更新或新增小工具执行接口
        if (list!= null && list.size() > 0) {//更新小工具执行接口调用结果
            smallTool = list.get(0);
            smallTool.setParameter(parameter);
            smallTool.setInterfaceType(smallToolDTO.getInterfaceType());
            int i = iSmallToolEngine.SmallToolUpdate(smallTool);
            logger.info("修改小工具记录条数：" + i);
        } else {//新增小工具执行接口调用结果
            smallTool.setInterfaceId(smallToolDTO.getInterfaceId());
            smallTool.setParameter(parameter);
            smallTool.setInterfaceType(smallToolDTO.getInterfaceType());
            smallTool.setIsDelete(1);
            int i = iSmallToolEngine.SmallToolInsert(smallTool);
            logger.info("更新小工具记录条数：" + i);
        }
    }


}
