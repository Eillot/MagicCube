package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.CaseManage;
import com.simon.magiccube.facade.dto.CaseManageDTO;
import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 2:48 PM
 * @File : ICaseManageEngine
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface ICaseManageEngine {

    /**
     * 查询全部用例
     * @param
     * @return
     */
    List<CaseManage> selectAllCase(CaseManage caseManage);

    List<CaseManage> selectAllCaseBySceneId(CaseManage caseManage);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param caseManage 实例对象
     * @return 对象列表
     */
    List<CaseManage> caseManageQueryAll(CaseManage caseManage);

    /**
     * 通过sceneID查询用例数据
     * @param sceneId
     * @return
     */
    List<CaseManage> caseQueryListBySceneId(String sceneId);

    /**
     * 根据caseName模糊查询用例
     * @param caseName
     * @return
     */
    List<CaseManage> caseQueryByCaseName(String caseName);


    /**
     * 根据id查询用例
     * @param id
     * @return
     */
    List selectCaseById(Integer id);

    /**
     * 根据id查询用例
     * @param id
     * @return
     */
    CaseManage queryCaseById(Integer id);

    /**
     * 查询用例列表数据
     * @return
     */
    List<CaseManage> caseListQueryAll(CaseManage caseManage);

    List<CaseManage> caseListQueryAllForScene(CaseManage caseManage);


    /**
     * 查询所有用例id
     * @return
     */
    List<CaseManage> selectCaseId();

    /**
     * 新增数据
     *
     * @param caseManage 实例对象
     * @return 影响行数
     */
    int caseManageInsert(CaseManage caseManage);

    /**
     * 修改数据
     *
     * @param caseManage 实例对象
     * @return 影响行数
     */
    int CaseManageUpdate(CaseManage caseManage);

    /**
     * 删除用例,将is_delete置为0
     * @param id
     * @return
     */
    int CaseManageDelete(Integer id);

    int selectCaseListPageTotal(CaseManage caseManage);


}
