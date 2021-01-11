package com.simon.magiccube.engine.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.*;

/**
 * @version: java version 1.8+
 * @Author : simon
 * @Explain :
 * @contact:
 * @Time : 2019-12-13  18:44
 * @File : HttpClientUtil
 * @Software: IntelliJ IDEA 2018.3
 */

public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final String CHARSET_UTF8 = "UTF-8";

    private static RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(20000).setConnectTimeout(20000).setSocketTimeout(20000)
            .build();

    private static final String pubToken="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvY2VhbkBhZG1pbiIsInVzZXJfbmFtZSI6Im9jZWFuQGFkbWluIiwiZXhwIjoxNTg3NjI0MTk2LCJpYXQiOjE1ODQ5NDU3OTZ9.wMUzyurdhsRNyWT9DAC425gpEPG8OmC42hxEZYxH7Kk";
    private HttpClientUtil() {

    }

    public static String sendGetRequest(String url) throws HttpUtilException {
        return execute(new HttpGet(url));
    }

    public static String sendPostRequest(String url) throws HttpUtilException {
        return execute(new HttpPost(url), new HashMap<>());
    }

    /**
     * Request Content-Type: application/x-www-form-urlencoded
     *
     * @param url
     * @param params
     * @return
     * @throws HttpUtilException
     */
    public static String sendPostRequest(String url, Map<String, String> params) throws HttpUtilException {
        HttpPost httpPost = new HttpPost(url);
        return execute(httpPost, params);
    }

    /**
     * Request Content-Type: application/x-www-form-urlencoded
     *
     * @param url
     * @param params
     * @return
     * @throws HttpUtilException
     */
    public static String sendGetRequest(String url, Map<String, String> params) throws HttpUtilException {
        HttpGet httpGet = new HttpGet();
        try {
            URIBuilder builder = new URIBuilder(url);
            if (null != params && !params.isEmpty()) {
                for (String key : params.keySet()) {
                    builder.addParameter(key, URLEncoder.encode(params.get(key), CHARSET_UTF8));
                }
            }
            httpGet.setURI(builder.build());
        } catch (URISyntaxException e) {
            logger.error("doGet(map)", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("doGet(map)", e);
        }
        return execute(httpGet);
    }

    /**
     * Request Content-Type: application/json
     *
     * @param url
     * @param json
     * @return
     * @throws HttpUtilException
     */
    public static String sendPostRequest(String url, String json) throws HttpUtilException {
        HttpPost httpPost = new HttpPost(url);
        return execute(httpPost, json);
    }

    /**
     * add by  simon
     *
     * Request Content-Type: application/json
     *
     * @param url
     * @param json
     * @return
     * @throws HttpUtilException
     */
    public static String doPostRequest(String url, String json) throws HttpUtilException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type",pubToken);
        return execute(httpPost, json);
    }

    /**
     * Request Content-Type: text/plain
     *
     * @param url
     * @param text
     * @return
     * @throws HttpUtilException
     */
    public static String sendPlainPostRequest(String url, String text) throws HttpUtilException {
        HttpPost httpPost = new HttpPost(url);
        return plainExecute(httpPost, text);
    }

    private static String execute(HttpGet httpGet) throws HttpUtilException {
        httpGet.setConfig(requestConfig);
        return doExecute(httpGet);
    }

    private static String execute(HttpPost httpPost, Map<String, String> params) throws HttpUtilException {
        httpPost.setConfig(requestConfig);
        if (null != params && !params.isEmpty()) {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            Set<Map.Entry<String, String>> set = params.entrySet();
            for (Map.Entry<String, String> entry : set) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, CHARSET_UTF8));
            } catch (UnsupportedEncodingException e) {
                logger.error("execute(MAP)", e);
                throw new HttpUtilException(e);
            }
        }
        return doExecute(httpPost);
    }

    private static String execute(HttpPost httpPost, String json) throws HttpUtilException {
        if (StringUtils.isEmpty(json)) {
            return "";
        }
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-type", "application/json; charset=utf-8");
        try {
            httpPost.setEntity(new StringEntity(json, CHARSET_UTF8));
        } catch (UnsupportedCharsetException e) {
            logger.error("execute(JSON)", e);
            throw new HttpUtilException(e);
        }
        return doExecute(httpPost);
    }

    private static String plainExecute(HttpPost httpPost, String text) throws HttpUtilException {
        if (StringUtils.isEmpty(text)) {
            return "";
        }
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-type", "text/plain; charset=utf-8");
        try {
            httpPost.setEntity(new StringEntity(text, CHARSET_UTF8));
        } catch (UnsupportedCharsetException e) {
            logger.error("execute(text)", e);
            throw new HttpUtilException(e);
        }
        return doExecute(httpPost);
    }

    private static String doExecute(HttpUriRequest httpUriRequest) throws HttpUtilException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpUriRequest);
            return EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF8);
        } catch (IOException e) {
            logger.error("doExecute() -> ", e);
            throw new HttpUtilException(e);
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    logger.error("doExecute() -> ", e);
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("doExecute() -> ", e);
                }
            }
        }
    }

    public static class HttpUtilException extends Exception {
        private HttpUtilException(Exception e) {
            super(e);
        }
    }

    /**
     * add by  simon
     * <p>
     * 自定义post请求-- 提交json数据
     *
     * @param url          接口URL地址
     * @return response
     */
    public static String sendPostRequestByJsonStr(String url, String json) {

        //post请求接口返回值
        String response = null;

        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;

            try {
                httpclient = HttpClients.createDefault();
                HttpPost httppost = new HttpPost(url);
                httppost.setHeader("Accept", "*/*");
                //httppost.setHeader("User-Agent", USER_AGENT);
                httppost.setHeader("Content-Type", "application/json");
                httppost.setHeader("Authorization", pubToken);

                httppost.setConfig(requestConfig);
                StringEntity stringentity = new StringEntity(json,
                        ContentType.create("text/json", CHARSET_UTF8));
                httppost.setEntity(stringentity);

                //发起post网络请求
                httpresponse = httpclient.execute(httppost);

                //检查网络请求结果码
                int resultcode = httpresponse.getStatusLine().getStatusCode();
                if (resultcode == HttpStatus.SC_OK) {

                    response = EntityUtils.toString(httpresponse.getEntity(),"UTF-8");

                    logger.info("网络请求成功: " + "Response: " + resultcode);
                } else {
                    logger.info("网络请求错误：" + "ResultCode :" + resultcode);
                }

            } finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    /**
     * 自定义get请求---提交 json String 字符串
     * @param url
     * @return
     */
    public static String sendGetRequestByjson(String url, Map<String ,String> getjson) {

        String response  = null;

        //自定义HttpGet报头属性
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "*/*");
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("Authorization", pubToken);

        //加载form 表单数据
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        if(getjson!=null){
            for (Map.Entry<String, String> entry : getjson.entrySet()) {
                formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        try
        {
            //发起get网络请求
            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse httpResponse = httpClient.execute(httpGet);

            //检查网络请求结果码
            int resultcode=httpResponse.getStatusLine().getStatusCode();
            if (resultcode == HttpStatus.SC_OK){
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, "UTF-8");
                logger.info("网络请求成功: " + "ResultCode: " + resultcode);
            }else {
                logger.info("网络请求错误：ResultCode :%d", resultcode);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
