package com.simon.magiccube.dao.mapper;

import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.dao.domain.AssertData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 验证点表(AssertData)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-27 18:18:48
 */

@Component
public interface AssertDataMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param testdataid 主键
     * @return 实例对象
     */
    AssertData AssertDataQueryBytestDataId(String testdataid);

    List<AssertData> AssertDataListQueryByTestDataId(String testdataid);

    /**
     * 通过ID查询单条数据
     ** @return 实例对象
     */
    List AssertDataQueryById(AssertData assertData);

    AssertData findById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AssertData> AssertDataQueryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param assertData 实例对象
     * @return 对象列表
     */
    List<AssertData> AssertDataQueryAll(AssertData assertData);

    List AssertDataQueryAllList(AssertData assertData);

    /**
     * 新增数据
     *
     * @param assertData 实例对象
     * @return 影响行数
     */
    int AssertDataInsert(AssertData assertData);

    /**
     * 修改数据
     *
     * @param assertData 实例对象
     * @return 影响行数
     */
    int AssertDataUpdate(AssertData assertData);

    /**
     * 通过testDataId修改数据
     * @param assertData
     * @return
     */
    int AssertDataUpdateByTestDataId(AssertData assertData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int AssertDataDeleteById(Integer id);

}