package com.simon.magiccube.engine.impl;

import com.simon.magiccube.dao.domain.SceneManage;
import com.simon.magiccube.dao.mapper.SceneManageMapper;
import com.simon.magiccube.engine.IScenesManageEngine;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** @Author : huorh  @Time : 2020/05/25 17:12 PM @Content : 场景管理 */
@Component
public class SceneManageEngineImp implements IScenesManageEngine {

  @Autowired private SceneManageMapper sceneManageMapper;

  @Override
  public List<SceneManage> sceneManageQueryAll(SceneManage sceneManage) {

    return sceneManageMapper.SceneManageQueryAll(sceneManage);
  }
}
