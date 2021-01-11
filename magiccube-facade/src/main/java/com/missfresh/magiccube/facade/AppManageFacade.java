package com.simon.magiccube.facade;

import com.simon.magiccube.facade.dto.AppManageDTO;
import com.simon.magiccube.facade.vo.AppManageVO;

import javax.servlet.ServletException;
import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/30 5:58 PM
 * @File : AppManageFacade
 * @Software: IntelliJ IDEA 2018.1.8
 */

public interface AppManageFacade {

//    CommonResult appManageInsertUser(AppManageDTO appManageDTO)throws ServletException;

    /**
     * 创建应用
     */
    int createApp(AppManageDTO appManageDTO);

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
    List<AppManageVO> selectAllApp(AppManageDTO appManageDTO);

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