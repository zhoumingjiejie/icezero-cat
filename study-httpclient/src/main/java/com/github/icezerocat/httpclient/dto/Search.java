package com.github.icezerocat.httpclient.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 搜索条件值
 * <p>
 * Created by zmj
 * On 2019/8/28.
 *
 * @author 0.0.0
 */
@Data
public class Search implements Serializable {

    public Search() {

    }

    public Search(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    /**
     * 搜索、过滤、排序字段
     */
    private String field;
    /**
     * 搜索、过滤、排序值
     */
    private Object value;
    /**
     * 搜索类型（日，月和基本类型）
     */
    private String type;
    /**
     * 日期格式
     */
    private String formatDate;
    /**
     * 排序方式
     */
    private String sort;
}
