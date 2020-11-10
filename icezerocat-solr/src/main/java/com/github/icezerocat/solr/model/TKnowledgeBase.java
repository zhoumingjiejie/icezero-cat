package com.github.icezerocat.solr.model;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:  知识库
 * Date: 2020-11-09 18:41:27
 *
 * @author 0.0
 */
@Data
public class TKnowledgeBase implements Serializable {

    private static final long serialVersionUID = 111280482044909401L;

    @Field("KNOWLEDGE_ID")
    private Long knowledgeId;

    @Field("QUESTION")
    private String question;

    @Field("ANSWER")
    private String answer;

    @Field("URL")
    private String url;

    @Field("FREQUENCY")
    private Long frequency;

    /**
     * ????˰??1????˰??2????˰
     */
    @Field("TYPE")
    private Long type;

    /**
     * ???
     */
    @Field("DISTRICT_CODE")
    private String districtCode;

    @Field("PUBLISH_TIME")
    private Date publishTime;

    @Field("USE_MONTH")
    private Long useMonth;

    @Field("NO_USE_MONTH")
    private Long noUseMonth;

    @Field("FREQUENCY_MONTH")
    private Long frequencyMonth;

}
