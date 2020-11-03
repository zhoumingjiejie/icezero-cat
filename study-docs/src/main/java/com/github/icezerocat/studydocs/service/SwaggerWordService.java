package com.github.icezerocat.studydocs.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Description: swagger转word服务
 * CreateDate:  2020/10/22 23:47
 *
 * @author zero
 * @version 1.0
 */
public interface SwaggerWordService {

    /**
     * 生成table数据通过swaggerUrl
     *
     * @param swaggerUrl swagger在线文档json访问地址
     * @return table数据
     */
    Map<String, Object> tableList(String swaggerUrl);

    /**
     * 生成table数据通过swaggerUrl,忽略请求类型不分类
     *
     * @param swaggerUrl swagger在线文档json访问地址
     * @return table数据
     */
    Map<String, Object> tableListAndIgnoreRequestType(String swaggerUrl);

    /**
     * 生成table数据通过json字符串
     *
     * @param jsonStr swagger在线文档的json数据
     * @return table数据
     */
    Map<String, Object> tableListFromString(String jsonStr);

    /**
     * ,忽略请求类型不分类,忽略请求类型不分类
     *
     * @param jsonStr swagger在线文档的json数据
     * @return table数据
     */
    Map<String, Object> tableListFromStringAndIgnoreRequestType(String jsonStr);

    /**
     * 获取table数据通过json文件
     *
     * @param jsonFile swagger在线文档的json文件
     * @return table数据
     */
    Map<String, Object> tableList(MultipartFile jsonFile);
}
