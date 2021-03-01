package com.github.icezerocat.httpclient.web.controller;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.github.icezerocat.httpclient.dto.HttpParam;
import com.github.icezerocat.httpclient.manage.ApiManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: http控制器
 * CreateDate:  2021/2/26 16:38
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@RestController
public class HttpController {

    private final ApiManage apiManage;

    public HttpController(ApiManage apiManage) {
        this.apiManage = apiManage;
    }

    /**
     * http发送请求
     *
     * @param httpParam          请求数据体
     * @param httpServletRequest 请求
     * @return 请求结果
     * @throws HttpProcessException 请求异常
     */
    @RequestMapping("send")
    public String send(@RequestBody HttpParam httpParam, HttpServletRequest httpServletRequest) throws HttpProcessException {
        String httpMethod = httpParam.getHttpMethod();
        httpMethod = httpMethod == null ? httpServletRequest.getMethod() : httpMethod;
        log.warn("{}\t{}", httpParam, httpMethod);
        String res = this.apiManage.send(httpParam.getParamMap(), httpParam.getHeaders(), httpMethod, httpParam.getUrl());
        log.info("{}", res);
        return res;
    }
}
