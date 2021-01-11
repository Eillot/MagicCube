package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.ProductManage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 业务线表(ProductManage)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:54
 */

@Component
public interface ProductManageMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    String ProductManageQueryById(int id);

    /**
     * 根据id查询业务线名称
//     * @param id
     * @return
     */
//    String ProductNameQueryById(int id);
    ProductManage ProductNameQueryById(int id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ProductManage> ProductManageQueryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param productManage 实例对象
     * @return 对象列表
     */
    List<ProductManage> ProductManageQueryAll(ProductManage productManage);

    /**
     * 查询所有业务线数据
     * @return
     */
    List<ProductManage> ProductSelectAll();

    /**
     * 新增数据
     *
     * @param productManage 实例对象
     * @return 影响行数
     */
    int ProductManageInsert(ProductManage productManage);

    /**
     * 修改数据
     *
     * @param productManage 实例对象
     * @return 影响行数
     */
    int ProductManageUpdate(ProductManage productManage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int ProductManageDeleteById(Integer id);

}