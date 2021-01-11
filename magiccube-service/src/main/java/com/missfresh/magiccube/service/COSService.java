package com.simon.magiccube.service;

import java.io.File;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/3/3 11:01 上午
 */
public interface COSService {

    public void asyncUpload(File file, String name);

    public void syncUpload(File file, String name);

    public void asyncUpload(String content, String name);

    public void syncUpload(String content, String name);

    public String download(String name);


}
