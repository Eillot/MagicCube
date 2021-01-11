package com.simon.magiccube.engine;
/*
*@author  simon
*@data 2020/7/23 14:56
*/

import com.simon.magiccube.dao.domain.ParamInfoEntity;
import com.simon.magiccube.dao.domain.res.ParamInfoListRes;
import com.simon.magiccube.engine.exception.UserException;
import com.simon.magiccube.facade.dto.ParamInfoDTO;

import java.util.List;

public interface ParamInfoService {

    /**
     * 新增参数化数据
     * @param paramInfoDTO
     * @return
     */
    String saveParamInfo(ParamInfoDTO paramInfoDTO);

    /**
     * 修改参数化数据
     * @param paramInfoDTO
     * @return
     */
    String updateParamInfo(ParamInfoDTO paramInfoDTO);

    /**
     * 删除参数化数据
     * @param id
     * @return
     */
    String delParamInfo(int id);

    /**
     * 根据id查询参数化数据
     * @param id
     * @return
     */
    List<ParamInfoEntity> searchById(int id);

    /**
     * 列表查询
     * @param paramInfoDTO
     * @return
     */
    ParamInfoListRes searchParamByList(ParamInfoDTO paramInfoDTO);

    /**
     * 执行参数动作
     * @param id
     * @return
     */
    Object doParam(int id);
}
