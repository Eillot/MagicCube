package com.simon.magiccube.engine.impl;

import com.simon.magiccube.dao.domain.DubboInterfaceManage;
import com.simon.magiccube.engine.IDubboInterfaceManageEngine;
import com.simon.magiccube.facade.dto.DubboInterfaceManageDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 4:21 PM
 * @File : DubboInterfaceManageEngineImp
 * @Software: IntelliJ IDEA 2018.1.8
 */
@Component
public class DubboInterfaceManageEngineImp implements IDubboInterfaceManageEngine {

    @Override
    public DubboInterfaceManage dubboInterfaceManageQueryById(Integer id) {
        return null;
    }

    @Override
    public List<DubboInterfaceManage> dubboInterfaceManageQueryAll(DubboInterfaceManageDTO dubboInterfaceManageDTO) {
        return null;
    }

    @Override
    public int dubboInterfaceManageInsert(DubboInterfaceManageDTO dubboInterfaceManageDTO) {
        return 0;
    }

    @Override
    public int dubboInterfaceManageUpdate(DubboInterfaceManageDTO dubboInterfaceManageDTO) {
        return 0;
    }

    @Override
    public int dubboInterfaceManageDeleteById(Integer id) {
        return 0;
    }
}
