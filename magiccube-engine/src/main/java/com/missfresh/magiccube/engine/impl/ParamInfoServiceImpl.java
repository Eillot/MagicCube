package com.simon.magiccube.engine.impl;
/*
*@author  simon
*@data 2020/7/23 14:58
*/

import com.github.pagehelper.util.StringUtil;
import com.simon.magiccube.dao.domain.PaginationQuery;
import com.simon.magiccube.dao.domain.ParamInfoEntity;
import com.simon.magiccube.dao.domain.res.ParamInfoListRes;
import com.simon.magiccube.dao.mapper.ParamInfoMapper;
import com.simon.magiccube.engine.ParamInfoService;
import com.simon.magiccube.engine.SQLService;
import com.simon.magiccube.engine.enums.ParamInfoEnum;
import com.simon.magiccube.engine.enums.ResponseEnum;
import com.simon.magiccube.facade.dto.ParamInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("ParamInfoService")
public class ParamInfoServiceImpl implements ParamInfoService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ParamInfoMapper paramInfoMapper;

    @Resource
    private SQLService sqlService;

    @Override
    public String saveParamInfo(ParamInfoDTO paramInfoDTO) {
        ParamInfoEntity paramInfoEntity = new ParamInfoEntity();
        BeanUtils.copyProperties(paramInfoDTO,paramInfoEntity);
        String paramName = paramInfoEntity.getParamName();
        String paramValue = paramInfoEntity.getParamValue();
        String paramContent = paramInfoEntity.getParamContent();
        String access = paramInfoEntity.getAccess();
        //基本信息不能为空
        if (StringUtil.isEmpty(paramName) || StringUtil.isEmpty(paramValue) || StringUtil.isEmpty(paramContent)|| StringUtil.isEmpty(access)) {
            return ResponseEnum.NULL.getMessage();
        } else {
            logger.info("入参信息 param={}",paramInfoEntity);
            //保存信息
            int saveParam = paramInfoMapper.saveParamInfo(paramInfoEntity);
            //校验保存信息是否存在
            if (saveParam!=0){
                return ResponseEnum.SUCCESS200.getMessage();
            }
        }
        return ResponseEnum.FAILED.getMessage();
    }

    @Override
    public String updateParamInfo(ParamInfoDTO paramInfoDTO) {
        ParamInfoEntity paramInfoEntity = new ParamInfoEntity();
        BeanUtils.copyProperties(paramInfoDTO,paramInfoEntity);
        int id = paramInfoEntity.getId();
        //判断修改信息是否存在
        if (paramInfoMapper.searchById(id).size()!=0){
            //修改信息
            int updateParam = paramInfoMapper.updateParamInfo(paramInfoEntity);
            //修改是否成功
            if (updateParam!=0){
                return ResponseEnum.SUCCESS200.getMessage();
            }else {
                return ResponseEnum.FAILED.getMessage();
            }
        }
        return ResponseEnum.NON.getMessage();
    }

    @Override
    public String delParamInfo(int id) {
        try {
            //删除信息
            paramInfoMapper.delParamInfo(id);
            return ResponseEnum.SUCCESS200.getMessage();
        } catch (Exception e) {
            return ResponseEnum.FAILED.getMessage();
        }
    }

    @Override
    public List<ParamInfoEntity> searchById(int id) {
        List<ParamInfoEntity> search = paramInfoMapper.searchById(id);
        return search;
    }

    @Override
    public ParamInfoListRes searchParamByList(ParamInfoDTO paramInfoDTO) {
        ParamInfoListRes paramInfoListRes = new ParamInfoListRes();
        ParamInfoEntity paramInfoEntity = new ParamInfoEntity();
        BeanUtils.copyProperties(paramInfoDTO,paramInfoEntity);
        PaginationQuery pagination = new PaginationQuery();
        pagination.setPageNum(paramInfoDTO.getPageNo());
        pagination.setPageSize(paramInfoDTO.getPageSize());
        paramInfoEntity.setPagination(pagination);

        int total = paramInfoMapper.searchParamCount(paramInfoEntity);
        if (total==0){
            paramInfoListRes.setTotal(total);
            return paramInfoListRes;
        }
        paramInfoListRes.setParamInfoEntities(paramInfoMapper.searchParamByList(paramInfoEntity));
        paramInfoListRes.setTotal(total);
        return paramInfoListRes;
    }

    @Override
    public Object doParam(int id) {
        Object obj = null;
        //根据id获取信息，判断是否存在
        List<ParamInfoEntity> list = paramInfoMapper.searchById(id);
        if (list.size()==0){
            return ResponseEnum.NON.getMessage();
        }
        //获取类型
        int type = list.get(0).getType();
        //调用MySQL引擎
        if (ParamInfoEnum.SQL.getCode()==type){
            String addressId = String.valueOf(list.get(0).getAddressId());
            String dbSentence = list.get(0).getParamContent();
            String access = list.get(0).getAccess();
            if (StringUtil.isEmpty(addressId) || StringUtil.isEmpty(dbSentence) || StringUtil.isEmpty(access)) {
                return ResponseEnum.NULL.getMessage();
            }
            obj = sqlService.sqlByParam(addressId,dbSentence,access);
        }
        //调用用例执行
        if (ParamInfoEnum.CASE.getCode()==type){

        }
        //输入值信息转换
        if(ParamInfoEnum.INPUT.getCode()==type){
            String value = list.get(0).getParamContent();
            if (StringUtil.isEmpty(value)){
                return ResponseEnum.NULL.getMessage();
            }
            obj = value;
        }

        return obj;
    }
}
