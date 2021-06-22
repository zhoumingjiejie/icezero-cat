package com.github.icezerocat.study.es.repository;


import com.github.icezerocat.study.es.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Description: ItemRepository
 * CreateDate:  2020/6/8 22:42
 *
 * @author zero
 * @version 1.0
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {
    /**
     * 通过title
     *
     * @param title 标题
     * @return item对象
     */
    Iterable<Item> findByTitle(String title);


    /**
     * 价格区间
     *
     * @param price1 价格1
     * @param price2 价格2
     * @return 列表
     */
    List<Item> findByPriceBetween(Double price1, Double price2);
}