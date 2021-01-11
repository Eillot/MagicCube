package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.SceneForm;
import com.simon.magiccube.dao.domain.SceneManage;
import com.simon.magiccube.facade.support.CommonResult;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/6/16 11:30 上午
 */
public interface SceneManageService {
    /** 查询场景列表 */
    CommonResult sceneList(SceneManage sceneManage);

    /** 新增场景 */
    CommonResult sceneAdd(SceneForm sceneForm);

    /** 更新场景 */
    CommonResult sceneUpdate(SceneForm SceneForm);

    /** 删除场景 */
    CommonResult sceneDelete(SceneManage sceneManage);

    /** 执行场景 */
    CommonResult sceneExecute(Integer sceneId);

    /** 复制场景 */
    CommonResult sceneCopy(Integer sceneId);

    /** 获取场景详情 */
    CommonResult sceneDetail(Integer sceneId);

    int SceneManageInsert(SceneManage sceneManage);

    int SceneManageDelete(Integer id);

    List<SceneManage> SceneManageListQueryById(Integer id);

    int SceneManageUpdate(SceneManage sceneManage);



}
