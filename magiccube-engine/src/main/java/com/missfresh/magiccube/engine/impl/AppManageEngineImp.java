package com.simon.magiccube.engine.impl;

import com.simon.magiccube.dao.domain.AppManage;
import com.simon.magiccube.dao.mapper.AppManageMapper;
import com.simon.magiccube.engine.IAppManageEngine;
import com.simon.magiccube.engine.util.DTOtoEntity;
import com.simon.magiccube.facade.dto.AppManageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 4:20 PM
 * @File : AppManageEngineImp
 * @Software: IntelliJ IDEA 2018.1.8
 */

@Component
public class AppManageEngineImp implements IAppManageEngine {

    @Autowired
    private AppManageMapper appManageMapper;

    private AppManage appManage = new AppManage();

    @Override
    public int createApp(AppManageDTO appManageDTO) {
        DTOtoEntity.transalte(appManageDTO, appManage);
        int reslutcode = appManageMapper.AppManageInsert(appManage);
        return reslutcode;
    }

    @Override
    public String selectAppByID(Integer id) {
        appManage = appManageMapper.AppManageQueryById(id);
        return appManageMapper.toString();
    }

    @Override
    public List<AppManage> selectAllApp(AppManageDTO appManageDTO) {
        DTOtoEntity.transalte(appManageDTO, appManage);
        List<AppManage> appManageList = appManageMapper.AppManageQueryAll(appManage);
        return appManageList;
    }

    @Override
    public int updateApp(AppManageDTO appManageDTO) {
        DTOtoEntity.transalte(appManageDTO, appManage);
        appManageMapper.AppManageUpdate(appManage);
        return 0;
    }

    @Override
    public int deleteApp(Integer id) {
        appManageMapper.AppManageDeleteById(id);
        return 0;
    }
}
