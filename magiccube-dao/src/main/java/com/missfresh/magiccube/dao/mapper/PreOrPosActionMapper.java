package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.PreOrPosAction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 前置/后置动作(PreOrPosAction)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:54
 */
@Component
public interface PreOrPosActionMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PreOrPosAction PreOrPosActionQueryById(Integer id);

    /**
     * 查询前后置列表数据
     * @return
     */
    List<PreOrPosAction> PreOrPosActionListQueryAll();

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PreOrPosAction> PreOrPosActionQueryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param preOrPosAction 实例对象
     * @return 对象列表
     */
    List<PreOrPosAction> PreOrPosActionQueryAll(PreOrPosAction preOrPosAction);

    /**
     * 新增数据
     *
     * @param preOrPosAction 实例对象
     * @return 影响行数
     */
    int PreOrPosActionInsert(PreOrPosAction preOrPosAction);

    /**
     * 修改数据
     *
     * @param preOrPosAction 实例对象
     * @return 影响行数
     */
    int PreOrPosActionUpdate(PreOrPosAction preOrPosAction);

    /**
     * 通过主键删除（将isDelete置为0）
     * @param preOrPosAction
     * @return
     */
    int PreOrPosActionDeleteToIsDeleteById(PreOrPosAction preOrPosAction);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int PreOrPosActionDeleteById(Integer id);

}