package com.simon.magiccube.engine.dto.request;

import lombok.Data;

/**
 * <p>http用例入参请求类</p>
 *
 * @author ：simon
 * @date ：Created in 2020-05-13 11:50
 */
@Data
public class HttpCaseRequest {

    private String interfaceUrl;

    private String postJsonRequest;
}
