package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.TestData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 测试数据表(TestData)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:54
 */

@Component
public interface TestDataMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param caseid 主键
     * @return 实例对象
     */
    TestData TestDataQueryByCaseId(String caseid);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TestData TestDataQueryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TestData> TestDataQueryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param testData 实例对象
     * @return 对象列表
     */
    List<TestData> TestDataQueryAll(TestData testData);

    /**
     * 新增数据
     *
     * @param testData 实例对象
     * @return 影响行数
     */
    int TestDataInsert(TestData testData);

    /**
     * 修改数据
     *
     * @param testData 实例对象
     * @return 影响行数
     */
    int TestDataUpdate(TestData testData);

    /**
     * 通过caseId修改数据
     * @param testData
     * @return
     */
    int TestDataUpdateByCaseId(TestData testData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int TestDataDeleteById(Integer id);

    List TestDataQueryListById(TestData testData);



}