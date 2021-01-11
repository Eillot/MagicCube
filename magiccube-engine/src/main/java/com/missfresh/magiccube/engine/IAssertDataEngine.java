package com.simon.magiccube.engine;

import com.alibaba.fastjson.JSONObject;
import com.simon.magiccube.dao.domain.AssertData;
import com.simon.magiccube.facade.support.CommonResult;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 2:45 PM
 * @File : IAssertDataEngine
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface IAssertDataEngine {

    AssertData AssertDataQueryBytestDataId(String testdataid) ;

    List<AssertData> AssertDataListQueryByTestDataId(String testdataid);

    CommonResult AssertDataQueryBytestDataIdRe(String testdataid) ;
    /**
     * 通过实体作为筛选条件查询
     *
     * @param assertData 实例对象
     * @return 对象列表
     */
    List<AssertData> AssertDataQueryAll(AssertData assertData);

    List AssertDataQueryAllList(AssertData assertData);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    List AssertDataQueryById(AssertData assertData);

    /**
     * 新增数据
     *
     * @param assertData 实例对象
     * @return 影响行数
     */
    int AssertDataInsert(AssertData assertData);

    /**
     * 通过testDataId修改数据
     * @param assertData
     * @return
     */
    int AssertDataUpdateByTestDataId(AssertData assertData);

    /**
     * 根据主键id更新
     * @param assertData
     * @return
     */
    int AssertDataUpdate(AssertData assertData);
}
