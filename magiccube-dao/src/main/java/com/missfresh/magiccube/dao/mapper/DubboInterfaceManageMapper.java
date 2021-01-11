package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.DubboInterfaceManage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * dubbo接口表(DubboInterfaceManage)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:54
 */

@Component
public interface DubboInterfaceManageMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DubboInterfaceManage DubboInterfaceManageQueryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DubboInterfaceManage> DubboInterfaceManageQueryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param dubboInterfaceManage 实例对象
     * @return 对象列表
     */
    List<DubboInterfaceManage> DubboInterfaceManageQueryAll(DubboInterfaceManage dubboInterfaceManage);

    /**
     * 新增数据
     *
     * @param dubboInterfaceManage 实例对象
     * @return 影响行数
     */
    int DubboInterfaceManageInsert(DubboInterfaceManage dubboInterfaceManage);

    /**
     * 修改数据
     *
     * @param dubboInterfaceManage 实例对象
     * @return 影响行数
     */
    int DubboInterfaceManageUpdate(DubboInterfaceManage dubboInterfaceManage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int DubboInterfaceManageDeleteById(Integer id);

}