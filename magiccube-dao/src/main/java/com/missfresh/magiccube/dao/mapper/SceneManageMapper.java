package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.SceneManage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 场景管理(SceneManage)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:54
 */

@Component
public interface SceneManageMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SceneManage SceneManageQueryById(Integer id);

    List<SceneManage> SceneManageListQueryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SceneManage> SceneManageQueryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sceneManage 实例对象
     * @return 对象列表
     */
    List<SceneManage> SceneManageQueryAll(SceneManage sceneManage);

    /**
     * 新增数据
     *
     * @param sceneManage 实例对象
     * @return 影响行数
     */
    int SceneManageInsert(SceneManage sceneManage);

    /**
     * 修改数据
     *
     * @param sceneManage 实例对象
     * @return 影响行数
     */
    int SceneManageUpdate(SceneManage sceneManage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int SceneManageDeleteById(Integer id);

    int SceneManageDelete(Integer id);

}