package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.CaseRun;
import com.simon.magiccube.dao.domain.SceneRun;
import com.simon.magiccube.facade.support.CommonResult;

import java.util.List;

/**
 *
 * @author simon
 * @date 2020-06-05 18:18:59
 **/
public interface ISceneRunEngine {

    SceneRun selectSceneRunById(Integer id);

    int insertSceneRun(SceneRun sceneRun);

    int updateSceneRunByIdSelective(SceneRun sceneRun);

    Long selectSceneRunListPageTotal(SceneRun sceneRun);

    List<SceneRun> selectSceneRunListPage(SceneRun sceneRun);

    List<SceneRun> selectSceneRunList(SceneRun sceneRun);

    /**
     * 查询最新的一条数据
     *
     */
    List<SceneRun> selectSceneRunByNew(SceneRun sceneRun);

    /**
     * 通过sceneId查询数据最新数据
     * @param sceneId
     * @return
     */
    SceneRun selectSceneRunBySceneIdNew(String sceneId);




}
