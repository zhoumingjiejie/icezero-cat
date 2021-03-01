package com.github.icezerocat.httpclient.manage;

import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.common.HttpMethods;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.github.icezerocat.httpclient.dto.Search;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zmj
 * On 2020/3/4.
 *
 * @author 0.0.0
 */
@Slf4j
@Component
public class ApiManage {

    @Value("${oa.apiUrl:http://127.0.0.1:10007}")
    private String apiUrl;

    private static Map<String, HttpMethods> httpMethodsMap = null;

    /**
     * 发送http请求
     *
     * @param paramMap   请求参数
     * @param headers    请求头信息
     * @param httpMethod 请求方式（GET、POST等）
     * @param url        请求接口
     * @return 请求结果
     * @throws HttpProcessException 请求异常
     */
    public String send(Map<String, Object> paramMap, List<Search> headers, String httpMethod, String url) throws HttpProcessException {
        HttpConfig httpConfig = HttpConfig.custom();

        url = url.indexOf("/") == 0 ? url : ("/" + url);
        httpConfig.url(apiUrl + url);

        //设置请求方式
        if (httpMethodsMap == null) {
            initHttpMethodsMap();
        }
        httpConfig.method(httpMethodsMap.get(httpMethod.toUpperCase()));

        //设置headers
        if (headers != null && headers.size() > 0) {
            HttpHeader httpHeader = HttpHeader.custom();
            headers.forEach(search -> httpHeader.other(search.getField(), String.valueOf(search.getValue())));
            httpConfig.headers(httpHeader.build());
        }

        //设置请求参数
        if (paramMap != null && !paramMap.isEmpty()) {
            httpConfig.map(paramMap);
        }

        return HttpClientUtil.send(httpConfig);
    }

    /**
     * payload请求
     *
     * @param requestBody 请求数据
     * @param headers     请求头（key-value形式）
     * @param url         请求接口url
     * @return 结果
     */
    public JSONObject payload(String requestBody, List<Search> headers, String url) {

        JSONObject jsonObject = new JSONObject();

        Connection.Response postResponse;
        url = url.indexOf("/") == 0 ? url : ("/" + url);
        Connection connection = Jsoup.connect(apiUrl + url)
                .requestBody(requestBody)
                .method(Connection.Method.POST)
                //10分钟超时
                .timeout(10 * 60 * 1000)
                //忽略文档的内容类型
                .ignoreContentType(true);

        //设置headers
        if (headers != null && headers.size() > 0) {
            headers.forEach(search -> connection.header(search.getField(), String.valueOf(search.getValue())));
        }
        try {
            postResponse = connection.execute();
            jsonObject.put("statusCode", postResponse.statusCode());
            jsonObject.put("statusMessage", postResponse.statusMessage());
            jsonObject.put("data", postResponse.body());
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put("statusCode", "");
            jsonObject.put("statusMessage", "api调用出错：" + e.getMessage());
            jsonObject.put("data", "{}");
            e.printStackTrace();
            return jsonObject;
        }
    }

    /**
     * 初始化请求方法
     */
    private void initHttpMethodsMap() {
        httpMethodsMap = new HashMap<>(16);
        Class<HttpMethods> httpMethodsClass = HttpMethods.class;
        HttpMethods[] httpMethods = httpMethodsClass.getEnumConstants();
        try {
            Method getName = httpMethodsClass.getMethod("getName");
            for (HttpMethods httpMethod : httpMethods) {
                httpMethodsMap.put(String.valueOf(getName.invoke(httpMethod)), httpMethod);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
