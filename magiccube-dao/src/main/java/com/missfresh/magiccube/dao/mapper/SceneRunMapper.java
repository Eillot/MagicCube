package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.CaseManage;
import com.simon.magiccube.dao.domain.SceneRun;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 场景执行(SceneRun)表数据库访问层
 *
 * @author simon
 * @since 2019-12-27 18:18:54
 */

@Component
public interface SceneRunMapper {

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