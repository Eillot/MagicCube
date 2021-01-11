package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.CaseManage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 用例表(CaseManage)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:54
 */

@Component
public interface CaseManageMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    List CaseManageQueryById(Integer id);


    /**
     * 通过sceneID查询用例数据
     * @param sceneId
     * @return
     */
    List<CaseManage> caseQueryListBySceneId(String sceneId);

    /**
     * 通过ID查询单条数据
     * @param id
     * @return
     */
    CaseManage caseQueryListById(Integer id);


    /**
     * 查询用例列表数据
     * @return
     */
    List<CaseManage> CaseListQueryAll(CaseManage caseManage);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<CaseManage> CaseManageQueryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param caseManage 实例对象
     * @return 对象列表
     */
    List<CaseManage> CaseManageQueryAll(CaseManage caseManage);

    /**
     * 查询所有用例
     * @return
     */
    List<CaseManage> CaseQueryAll(CaseManage caseManage);

    /**
     * 根据caseName模糊查询用例
     * @param caseName
     * @return
     */
    List<CaseManage> CaseQueryByCaseName(String caseName);


    /**
     * 新增数据
     *
     * @param caseManage 实例对象
     * @return 影响行数
     */
    int CaseManageInsert(CaseManage caseManage);

    /**
     * 修改数据
     *
     * @param caseManage 实例对象
     * @return 影响行数
     */
    int CaseManageUpdate(CaseManage caseManage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int CaseManageDeleteById(Integer id);

    /**
     * 删除用例，将is_delete置为0
     * @param id
     * @return
     */
    int CaseManageDelete(Integer id);

    /**
     * 查询所有用例id
     * @return
     */
    List<CaseManage> SelectCaseId();

    int selectCaseListPageTotal(CaseManage caseManage);

}