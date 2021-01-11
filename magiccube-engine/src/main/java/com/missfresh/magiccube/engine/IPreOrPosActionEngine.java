package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.PreOrPosAction;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 3:08 PM
 * @File : IPreOrPosActionEngine
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface IPreOrPosActionEngine {

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
     * 通过实体作为筛选条件查询
     *
     * @param preOrPosAction 实例对象
     * @return 对象列表
     */
    List<PreOrPosAction> PreOrPosActionQueryAll(PreOrPosAction preOrPosAction);

    /**
     * 新增前后置数据
     * @param preOrPosAction
     * @return
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
}
