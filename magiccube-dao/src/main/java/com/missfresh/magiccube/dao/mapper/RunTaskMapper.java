package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.RunTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 执行任务表(RunTask)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:54
 */

@Component
public interface RunTaskMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RunTask RunTaskQueryById(Integer id);

    /**
     * 通过id查询所有数据
     * @param id
     * @return
     */
    List<RunTask> RunTaskQueryAllByID(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RunTask> RunTaskQueryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param runTask 实例对象
     * @return 对象列表
     */
    List<RunTask> RunTaskQueryAll(RunTask runTask);

    /**
     * 新增数据
     *
     * @param runTask 实例对象
     * @return 影响行数
     */
    int RunTaskInsert(RunTask runTask);

    /**
     * 修改数据
     *
     * @param runTask 实例对象
     * @return 影响行数
     */
    int RunTaskUpdate(RunTask runTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int RunTaskDeleteById(Integer id);

}