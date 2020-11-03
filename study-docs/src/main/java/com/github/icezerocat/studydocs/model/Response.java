package com.github.icezerocat.studydocs.model;

import java.io.Serializable;

/**
 * Description: 响应
 * CreateDate:  2020/10/22 23:42
 *
 * @author zero
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Response implements Serializable {
    /**
     * 返回参数
     */
    private String description;

    /**
     * 参数名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Response{" +
                "description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
