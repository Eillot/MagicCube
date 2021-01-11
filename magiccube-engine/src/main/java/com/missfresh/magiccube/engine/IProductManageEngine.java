package com.simon.magiccube.engine;

import com.simon.magiccube.dao.domain.ProductManage;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 3:08 PM
 * @File : IProductManageEngine
 * @Software: IntelliJ IDEA 2018.1.8
 */
public interface IProductManageEngine {

    /**
     * 根据id查询
//     * @param id
     * @return
     */
//    String selectProductNameByID(int id);
    ProductManage selectProductNameByID(int id);


    /**
     * 查询所有业务线数据
     * @return
     */
    List<ProductManage> ProductSelectAll();
}
