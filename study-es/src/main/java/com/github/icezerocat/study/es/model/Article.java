package com.github.icezerocat.study.es.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Description: 文章
 * CreateDate:  2020/5/26 22:36
 *
 * @author zero
 * @version 1.0
 */
@Data
@Document(indexName = "test")
public class Article {

    private Integer id;
    private String author;
    private String title;
    private String content;
}
