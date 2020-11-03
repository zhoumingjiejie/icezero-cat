package com.github.icezerocat.studydocs.model;

import java.io.Serializable;
import java.util.List;

/**
 * Description: api表单
 * CreateDate:  2020/10/22 23:38
 *
 * @author zero
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Table implements Serializable {
    /**
     * 大标题
     */
    private String title;
    /**
     * 小标题
     */
    private String tag;
    /**
     * url
     */
    private String url;

    /**
     * 描述
     */
    private String description;

    /**
     * 请求参数格式
     */
    private String requestForm;

    /**
     * 响应参数格式
     */
    private String responseForm;

    /**
     * 请求方式
     */
    private String requestType;

    /**
     * 请求体
     */
    private List<Request> requestList;

    /**
     * 返回体
     */
    private List<Response> responseList;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 返回参数
     */
    private String responseParam;

    /**
     * 返回属性列表
     */
    private ModelAttr modelAttr = new ModelAttr();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestForm() {
        return requestForm;
    }

    public void setRequestForm(String requestForm) {
        this.requestForm = requestForm;
    }

    public String getResponseForm() {
        return responseForm;
    }

    public void setResponseForm(String responseForm) {
        this.responseForm = responseForm;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public List<Response> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<Response> responseList) {
        this.responseList = responseList;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getResponseParam() {
        return responseParam;
    }

    public void setResponseParam(String responseParam) {
        this.responseParam = responseParam;
    }

    public ModelAttr getModelAttr() {
        return modelAttr;
    }

    public void setModelAttr(ModelAttr modelAttr) {
        this.modelAttr = modelAttr;
    }

    @Override
    public String toString() {
        return "Table{" +
                "title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", requestForm='" + requestForm + '\'' +
                ", responseForm='" + responseForm + '\'' +
                ", requestType='" + requestType + '\'' +
                ", requestList=" + requestList +
                ", responseList=" + responseList +
                ", requestParam='" + requestParam + '\'' +
                ", responseParam='" + responseParam + '\'' +
                ", modelAttr=" + modelAttr +
                '}';
    }
}
