package com.github.icezerocat.solr.model;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;

/**
 * Description:  主题
 * Date: 2020-11-09 18:09:58
 *
 * @author 0.0
 */
@Data
@SolrDocument(collection = "theme")
public class TGuideTheme implements Serializable {

    private static final long serialVersionUID = 5771686597477574885L;

    /**
     * 主题ID
     */
    @Field("THEME_ID")
    private String themeId;

    /**
     * 主题编码
     */
    @Field("THEME_CODE")
    private String themeCode;

    /**
     * 用户类型代码
     */
    @Field("USER_TYPE")
    private String userType;

    /**
     * 主题名称
     */
    @Field("THEME_NAME")
    private String themeName;

    /**
     * 主题简称
     */
    @Field("THEME_JC")
    private String themeJc;

    /**
     * 主题事项概述
     */
    @Field("THEME_SUMMARY")
    private String themeSummary;

    /**
     * 地市特有主题
     */
    @Field("BELONG_MU_ID")
    private String belongMuId;

    /**
     * 是否启用0未开通1开通
     */
    @Field("IS_OPEN")
    private String isOpen;

    /**
     * 是否删除0否1是
     */
    @Field("IS_DELETE")
    private String isDelete;

}
