package com.github.icezerocat.study.es.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Description: 搜索实体类
 * CreateDate:  2020/5/28 22:32
 *
 * @author zero
 * @version 1.0
 */
@Data
@Document(indexName = "item123")
public class Item {
    /**
     * 注意此处的@Id必须为springframework包下面的id   import org.springframework.data.annotation.Id;
     */
    @Id
    Long id;
    /**
     * 标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    String title;
    /**
     * 分类
     */
    @Field(type = FieldType.Keyword)
    String category;
    /**
     * 品牌
     */
    @Field(type = FieldType.Keyword, fielddata = true)
    String brand;
    /**
     * 价格
     */
    @Field(type = FieldType.Double)
    Double price;
    /**
     * 图片地址
     */
    @Field(type = FieldType.Keyword, index = false)
    String images;

    public Item(Long id, String title, String category, String brand, Double price, String images) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.images = images;
    }
}
