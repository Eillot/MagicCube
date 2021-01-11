package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.HttpInterfaceManage;
import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 3:07 PM
 * @File : IHttpInterfaceManageEngine
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface IHttpInterfaceManageEngine {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HttpInterfaceManage httpInterfaceManageQueryById(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param httpInterfaceManage 实例对象
     * @return 对象列表
     */
    List<HttpInterfaceManage> httpInterfaceManageQueryAll(HttpInterfaceManage httpInterfaceManage);

    /**
     * 新增数据
     *
     * @param httpInterfaceManage 实例对象
     * @return 影响行数
     */
    int httpInterfaceManageInsert(HttpInterfaceManage httpInterfaceManage);

    /**
     * 修改数据
     *
     * @param httpInterfaceManage 实例对象
     * @return 影响行数
     */
    int httpInterfaceManageUpdate(HttpInterfaceManage httpInterfaceManage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int httpInterfaceManageDeleteById(Integer id);
}
