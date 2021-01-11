package com.simon.magiccube.engine.impl;

import com.simon.magiccube.dao.domain.ProductManage;
import com.simon.magiccube.dao.mapper.ProductManageMapper;
import com.simon.magiccube.engine.IProductManageEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019/12/28 4:22 PM
 * @File : ProductManageEngineImp
 * @Software: IntelliJ IDEA 2018.1.8
 */

@Component
public class ProductManageEngineImp implements IProductManageEngine {
    @Autowired
    private ProductManageMapper productManageMapper;
    private ProductManage productManage = new ProductManage();

//    @Override
//    public String selectProductNameByID(int id) {
//        String productName =  productManageMapper.ProductNameQueryById(id);
//        return productName;
//    }


    @Override
    public ProductManage selectProductNameByID(int id) {
        return productManageMapper.ProductNameQueryById(id);
    }

    @Override
    public List<ProductManage> ProductSelectAll() {
        List<ProductManage> productManageList =  productManageMapper.ProductSelectAll();
        return productManageList;
    }
}
