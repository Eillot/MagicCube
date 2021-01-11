package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.AppManage;
import com.simon.magiccube.facade.dto.AppManageDTO;
import javax.servlet.ServletException;
import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 2:43 PM
 * @File : IAppManageEngine
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface IAppManageEngine {


    /**
     * 使用主键id查询唯一应用
     *
     * @param id
     * @return
     * @throws ServletException
     */
    String selectAppByID(Integer id);

    /**
     * 查询并获取全部应用
     *
     * @param appManageDTO
     * @return
     * @throws ServletException
     */
    List<AppManage> selectAllApp(AppManageDTO appManageDTO);

    /**
     * 创建应用
     */
    int createApp(AppManageDTO appManageDTO);


    /**
     * 更新应用数据
     *
     * @param appManageDTO
     * @return
     * @throws ServletException
     */
    int updateApp(AppManageDTO appManageDTO);

    /**
     * 使用主键id来删指定数据
     *
     * @param id
     * @return
     * @throws ServletException
     */
    int deleteApp(Integer id);


}
