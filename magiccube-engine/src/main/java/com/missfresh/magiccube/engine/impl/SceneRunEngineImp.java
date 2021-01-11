package com.simon.magiccube.engine.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.dao.domain.CaseRun;
import com.simon.magiccube.dao.domain.SceneRun;
import com.simon.magiccube.dao.mapper.CaseRunMapper;
import com.simon.magiccube.dao.mapper.SceneRunMapper;
import com.simon.magiccube.engine.ICaseRunEngine;
import com.simon.magiccube.engine.ISceneRunEngine;
import com.simon.magiccube.engine.common.ResourceManagement;
import com.simon.magiccube.facade.support.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author simon
 * @date 2020-06-05 18:18:59
 **/

@Component
public class SceneRunEngineImp implements ISceneRunEngine {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SceneRunMapper sceneRunMapper;


    @Override
    public SceneRun selectSceneRunById(Integer id) {
        return sceneRunMapper.selectSceneRunById(id);
    }

    @Override
    public int insertSceneRun(SceneRun sceneRun) {
        return sceneRunMapper.insertSceneRun(sceneRun);
    }

    @Override
    public int updateSceneRunByIdSelective(SceneRun sceneRun) {
        return sceneRunMapper.updateSceneRunByIdSelective(sceneRun);
    }

    @Override
    public Long selectSceneRunListPageTotal(SceneRun sceneRun) {
        return sceneRunMapper.selectSceneRunListPageTotal(sceneRun);
    }

    @Override
    public List<SceneRun> selectSceneRunListPage(SceneRun sceneRun) {
        return sceneRunMapper.selectSceneRunListPage(sceneRun);
    }

    @Override
    public List<SceneRun> selectSceneRunList(SceneRun sceneRun) {
        return sceneRunMapper.selectSceneRunList(sceneRun);
    }


    @Override
    public SceneRun selectSceneRunBySceneIdNew(String sceneId) {
        return sceneRunMapper.selectSceneRunBySceneIdNew(sceneId);
    }

    @Override
    public List<SceneRun> selectSceneRunByNew(SceneRun sceneRun) {
        List<SceneRun> sceneRunList = sceneRunMapper.selectSceneRunByNew(sceneRun);
        List result = new ArrayList();

        for(SceneRun sceneRunListNew : sceneRunList){
            //根据环境id，查询环境
            Integer enviromentId = Integer.valueOf(sceneRunList.get(0).getEnvironmentId());
            JSONObject resServerenvInfoList = JSONObject.parseObject(ResourceManagement.queryListResServerenvInfoById(enviromentId));
            JSONArray resServerenvInfoListResult = resServerenvInfoList.getJSONArray("result");
            if(resServerenvInfoListResult.size() == 0){
                logger.info("资源管理-环境查询结果为空，enviromentId:"+enviromentId);
            }else {
                String environmentName = String.valueOf(resServerenvInfoList.getJSONArray("result").getJSONObject(0).get("serverName"));
                sceneRunListNew.setEnvironmentName(environmentName);
                result.add(sceneRunListNew);
            }

        }


        return result;
    }
}
