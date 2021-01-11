package com.simon.magiccube.service.Imp;

import com.simon.magiccube.service.COSService;
import com.qcloud.cos.COS;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Time : 2020/3/3 11:18 上午
 */

@Service
public class COSServiceImpl implements COSService {

    private static final Logger logger = LoggerFactory.getLogger(COSServiceImpl.class);

    //腾讯云对象存储COS secretId 需要替换对应secretId
    private static final String COS_SECRET_ID = "AKID685xfEXUky4DF8pWabguCOSjtvlSfSjr";
    //腾讯云对象存储COS secretKey 需要替换对应secretKey
    private static final String COS_SECRET_KEY = "ABE2vgJm5FF8lIj2CRoFa9bzTs6C7uMf";
    //地域 Region
    private static final String COS_REGION_BJ = "ap-beijing";
    //COS 存储目录（对于腾讯云没有目录概念，只是为了便于理解）
    //需要在部署服务器上新建目录 /XXX/XXX/XXX/，否则文件写入失败，上传到腾讯云失败
    private static final String SUB_PACKAGE = "/data/logs/";
    //COS 存储桶
    private static final String COS_BUCKET="datafactory-1255613256";


    //创建大小为2的线程池
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);
    private final TransferManager transferManager = initTransferManager();

    /**
     * 根据COS客户端和大小为2的线程池，初始化TransferManager对象
     * @return
     */
    private TransferManager initTransferManager() {
        //1 初始化用户身份信息（secretId, secretKey）
        COSCredentials credentials = new BasicCOSCredentials(COS_SECRET_ID, COS_SECRET_KEY);
        // 2 设置 bucket 的区域
        //clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法
        ClientConfig clientConfig = new ClientConfig(new Region(COS_REGION_BJ));
        // 3 生成 COS 客户端
        COSClient cosClient = new COSClient(credentials, clientConfig);
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        return new TransferManager(cosClient, threadPool);
    }


    /**
     * 异步上传文件同步等待
     * @param file 文件
     * @param name 文件名
     */
    private void upload(File file, String name) {
        String key = SUB_PACKAGE + name;
        PutObjectRequest putObjectRequest = new PutObjectRequest(COS_BUCKET, key, file);
        Upload upload = transferManager.upload(putObjectRequest);
        try {
            upload.waitForUploadResult();
            logger.info("cos upload file success");
        } catch (InterruptedException | CosClientException e) {
            logger.error("cos upload file ex {}",e);
        }
    }


    /**
     * 字符串转文件
     * @param content
     * @param name
     * @return
     */
    public File stringToFile(String content,String name){
        String filename=SUB_PACKAGE + name;
        File file=new File(filename);
        try {
            Writer out =new FileWriter(file);
            out.write(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public void asyncUpload(File file, String name) {
        logger.info("cos start upload file");
        executorService.submit(()-> upload(file,name));
    }

    @Override
    public void syncUpload(File file, String name) {
        upload(file, name);
    }

    @Override
    public void asyncUpload(String content, String name) {
        logger.info("cos start upload file");
        executorService.submit(()-> upload(stringToFile(content,name),name));
    }

    @Override
    public void syncUpload(String content, String name) {
        upload(stringToFile(content,name), name);
    }

    @Override
    public String download(String name) {
        String key=SUB_PACKAGE + name;
        GetObjectRequest getObjectRequest = new GetObjectRequest(COS_BUCKET, key);
        COS cos=transferManager.getCOSClient();
        COSObject cosObject = cos.getObject(getObjectRequest);
        COSObjectInputStream cosObjectInputStream=cosObject.getObjectContent();

        //InputStream 转换为字符串
        BufferedReader reader = new BufferedReader(new InputStreamReader(cosObjectInputStream));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                cosObjectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
