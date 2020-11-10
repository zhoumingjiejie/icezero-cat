package com.github.icezerocat.solr.model;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;

/**
 * Description: solr对象数据
 * CreateDate:  2020/11/3 9:44
 *
 * @author zero
 * @version 1.0
 */
@Data
@SolrDocument(collection = "collection1")
public class Md implements Serializable {

    @Id
    @Field
    private int id;

    @Field("md_name")
    private String mdName;

    @Field("md_code")
    private String mdCode;
}
