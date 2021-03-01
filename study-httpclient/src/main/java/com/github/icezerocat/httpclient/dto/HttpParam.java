package com.github.icezerocat.httpclient.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Description: http发送请求需要的数据
 * CreateDate:  2021/2/26 16:41
 *
 * @author zero
 * @version 1.0
 */
@Data
public class HttpParam implements Serializable {
    /**
     * 请求参数
     */
    private Map<String, Object> paramMap;
    /**
     * 请求头信息
     */
    private List<Search> headers;
    /**
     * 请求方式（GET、POST等）
     */
    private String httpMethod;
    /**
     * 请求方式（GET、POST等）
     */
    private String url;
}
