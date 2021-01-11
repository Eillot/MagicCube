package com.simon.magiccube.dao.mapper;
/*
*@author  simon
*@data 2020/7/23 14:31
*/

import com.simon.magiccube.dao.domain.ParamInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@Mapper
public interface ParamInfoMapper {

    int saveParamInfo(ParamInfoEntity paramInfoEntity);

    int updateParamInfo(ParamInfoEntity paramInfoEntity);

    int delParamInfo(@Param("id") int id);

    List<ParamInfoEntity> searchById(@Param("id") int id);

    int searchParamCount(ParamInfoEntity paramInfoEntity);

    List<ParamInfoEntity> searchParamByList(ParamInfoEntity paramInfoEntity);
}
