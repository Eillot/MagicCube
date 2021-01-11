package com.simon.magiccube.dao.domain;

import java.util.Date;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain : image用户图片实体类
 * @contact:
 * @Time : 2019-12-13  15:23
 * @File : Image
 * @Software: IntelliJ IDEA 2018.3
 */

public class Image {

    /**
     * 主键
     */
    private Long id;

    /**
     * 图片名称
     */
    private String imageName;
    /**
     * 图片保存路径
     */
    private String imagePath;

    /**
     * 图片保存日期
     */
    private Date imageCreateTime;

    /**
     *  无参构造方法初始化
     */
    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getImageCreateTime() {
        return imageCreateTime;
    }

    public void setImageCreateTime(Date imageCreateTime) {
        this.imageCreateTime = imageCreateTime;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imageName='" + imageName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", imageCreateTime=" + imageCreateTime +
                '}';
    }
}
