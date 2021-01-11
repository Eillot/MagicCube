package com.simon.magiccube.dao.mapper;

import com.simon.magiccube.dao.domain.Image;
import org.springframework.stereotype.Component;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  15:28
 * @File : ImageMapper
 * @Software: IntelliJ IDEA 2018.3
 */

@Component
public interface ImageMapper {

    /**
     * 根据图片名称找到图片
     *
     * @param imageName
     * @return
     */
    Image findImageByImageName(String imageName);

    /**
     * 保存图片信息
     *
     * @param image
     */
    void insertImage(Image image);

    /**
     * 更新图片信息
     *
     * @param image
     */
    void updateImage(Image image);
}
