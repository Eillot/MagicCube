package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.CaseManage;
import com.simon.magiccube.dao.domain.DubboInterfaceManage;
import com.simon.magiccube.dao.domain.SceneManage;
import com.simon.magiccube.facade.dto.DubboInterfaceManageDTO;
import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 2:48 PM
 * @File : ICaseManageEngine
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface IScenesManageEngine {

    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<SceneManage> sceneManageQueryAll(SceneManage sceneManage);

}
