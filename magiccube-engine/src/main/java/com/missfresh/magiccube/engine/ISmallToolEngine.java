package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.SmallTool;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 3:10 PM
 * @File : ISmallToolEngine
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface ISmallToolEngine {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SmallTool SmallToolQueryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<SmallTool> SmallToolQueryAll(SmallTool smallTool);

    /**
     * 新增数据
     *
     * @return 影响行数
     */
    int SmallToolInsert(SmallTool smallTool);

    /**
     * 修改数据
     *
     * @return 影响行数
     */
    int SmallToolUpdate(SmallTool smallTool);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int SmallToolDeleteById(Integer id);

    /**
     * 小工具执行http请求
     * @param requestType
     * @param interUrl
     * @param parameter
     * @return
     */
    Object excuteHttpSmallTool(String requestType,String runEnv, String interUrl, String parameter);

    /**
     * 小工具执行dubbo请求
     * @param interUrl
     * @param method
     * @param zkAddress
     * @param zkVertion
     * @param paramTypes
     * @param params
     * @param applicationName
     * @return
     */
    Object excuteDubboSmallTool(String interUrl, String method, String zkAddress,String group, String zkVertion, String[] paramTypes, Object[] params, String applicationName);

}
