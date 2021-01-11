package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.HttpInterfaceManage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * http接口表(HttpInterfaceManage)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:54
 */

@Component
public interface HttpInterfaceManageMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HttpInterfaceManage HttpInterfaceManageQueryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HttpInterfaceManage> HttpInterfaceManageQueryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param httpInterfaceManage 实例对象
     * @return 对象列表
     */
    List<HttpInterfaceManage> HttpInterfaceManageQueryAll(HttpInterfaceManage httpInterfaceManage);

    /**
     * 新增数据
     *
     * @param httpInterfaceManage 实例对象
     * @return 影响行数
     */
    int HttpInterfaceManageInsert(HttpInterfaceManage httpInterfaceManage);

    /**
     * 修改数据
     *
     * @param httpInterfaceManage 实例对象
     * @return 影响行数
     */
    int HttpInterfaceManageUpdate(HttpInterfaceManage httpInterfaceManage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int HttpInterfaceManageDeleteById(Integer id);

}