package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.DubboInterfaceManage;
import com.simon.magiccube.facade.dto.DubboInterfaceManageDTO;
import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 3:07 PM
 * @File : IDubboInterfaceManageEngine
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface IDubboInterfaceManageEngine {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DubboInterfaceManage dubboInterfaceManageQueryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<DubboInterfaceManage> dubboInterfaceManageQueryAll(DubboInterfaceManageDTO dubboInterfaceManageDTO);

    /**
     * 新增数据
     *
     * @return 影响行数
     */
    int dubboInterfaceManageInsert(DubboInterfaceManageDTO dubboInterfaceManageDTO);

    /**
     * 修改数据
     *
     * @return 影响行数
     */
    int dubboInterfaceManageUpdate(DubboInterfaceManageDTO dubboInterfaceManageDTO);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int dubboInterfaceManageDeleteById(Integer id);

}
