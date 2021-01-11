package com.simon.magiccube.engine.impl;

import com.simon.magiccube.dao.domain.HttpInterfaceManage;
import com.simon.magiccube.engine.IHttpInterfaceManageEngine;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 4:21 PM
 * @File : HttpInterfaceManageEngineImp
 * @Software: IntelliJ IDEA 2018.1.8
 */
@Component
public class HttpInterfaceManageEngineImp implements IHttpInterfaceManageEngine {
    @Override
    public HttpInterfaceManage httpInterfaceManageQueryById(Integer id) {
        return null;
    }

    @Override
    public List<HttpInterfaceManage> httpInterfaceManageQueryAll(HttpInterfaceManage httpInterfaceManage) {
        return null;
    }

    @Override
    public int httpInterfaceManageInsert(HttpInterfaceManage httpInterfaceManage) {
        return 0;
    }

    @Override
    public int httpInterfaceManageUpdate(HttpInterfaceManage httpInterfaceManage) {
        return 0;
    }

    @Override
    public int httpInterfaceManageDeleteById(Integer id) {
        return 0;
    }
}
