package com.github.icezerocat.study.es.controller;

import com.github.icezerocat.study.es.model.Item;
import com.github.icezerocat.study.es.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Description: 弹性搜索操作控制器
 * CreateDate:  2020/5/26 22:34
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@RestController
public class ESOperationController {

    private final ItemRepository itemRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public ESOperationController(ElasticsearchRestTemplate elasticsearchRestTemplate, ItemRepository itemRepository) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.itemRepository = itemRepository;
    }


    @GetMapping("addIndexTest")
    public void addIndexTest() {
        this.elasticsearchRestTemplate.createIndex(Item.class);
        this.elasticsearchRestTemplate.putMapping(Item.class);
    }

    @GetMapping("create")
    public void create() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        this.itemRepository.saveAll(list);
    }

    @GetMapping("findById")
    public void findById() {
        Optional<Item> item = this.itemRepository.findById(2L);
        log.debug("{}", item);
    }

    @GetMapping("findAllSort")
    public void findAllSort() {
        Iterable<Item> items = this.itemRepository.findAll(Sort.by("price").descending());
        log.debug("{}", items);
    }

    @GetMapping("findByTitle")
    public void findByTitle() {
        Iterable<Item> items = this.itemRepository.findByTitle("手 机");
        log.debug("{}", items);
    }

    @GetMapping("testSearch")
    public void testSearch() {
        // 通过查询构建器构建查询条件
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "手X机");
        //执行查询
        Iterable<Item> items = this.itemRepository.search(matchQueryBuilder);
        log.debug("{}", items);
    }

    @GetMapping("findByPrice")
    public void findByPrice() {
        List<Item> byPriceBetween = this.itemRepository.findByPriceBetween(3699d, 4498d);
        log.debug("{}", byPriceBetween);
    }


    @GetMapping("testNative")
    public void testNative() {
        //构建自定义查询构建器
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //添加基本的查询条件
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "手XY机");
        //执行查询获取分页结果集
        nativeSearchQueryBuilder.withQuery(matchQueryBuilder);

        Page<Item> items = this.itemRepository.search(nativeSearchQueryBuilder.build());
        log.debug("items.getTotalElements() = " + items.getTotalElements());
        log.debug("items.getTotalPages() = " + items.getTotalPages());
        items.forEach(item -> log.debug("{}", item));

    }


    /**
     * 分页查询
     */
    @GetMapping("testNativeQuery")
    public void testNativeQuery() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本的分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("title", "手啊啊啊笑机"));

        // 初始化分页参数
        int page = 0;
        int size = 3;
        // 设置分页参数
        queryBuilder.withPageable(PageRequest.of(page, size));

        // 执行搜索，获取结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        // 打印总条数
        System.out.println(items.getTotalElements());
        // 打印总页数
        System.out.println(items.getTotalPages());
        // 每页大小
        System.out.println(items.getSize());
        // 当前页
        System.out.println(items.getNumber());
        items.forEach(System.out::println);
    }

    @GetMapping("testAggs")
    public void testAggs() {

        //初始化自定义构建查询器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //添加聚合
        queryBuilder.addAggregation(AggregationBuilders.terms("brandAgg").field("brand"));

        //添加结果集过滤不包括任何字段
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{}, null));

        //执行查询
        AggregatedPage<Item> itemPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());


        /*
        解析聚合结果集,根据聚合的类型以及字段类型,要进行强转,不然无法获取桶
         brand-是字符串类型的,聚合类型是词条类型的
        brandAgg-通过聚合名称获取聚合对象
        使用StringTerms强转的时候出现错误
         */

        ParsedStringTerms brandAgg = (ParsedStringTerms) itemPage.getAggregation("brandAgg");

        //获取桶
        List<? extends Terms.Bucket> buckets = brandAgg.getBuckets();

        //遍历输出
        buckets.forEach(bucket -> {
            System.out.println("bucket.getKeyAsString() = " + bucket.getKeyAsString());
            //获取条数
            System.out.println("bucket.getDocCount() = " + bucket.getDocCount());
        });

    }


    /**
     * 子聚合
     */
    @GetMapping("testSubAggs")
    public void testSubAggs() {

        //初始化自定义构建查询器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //添加聚合
        queryBuilder.addAggregation(AggregationBuilders.terms("brandAgg").field("brand").subAggregation(AggregationBuilders.avg("price_avg").field("price")));

        //添加结果集过滤不包括任何字段
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{}, null));

        //执行查询
        AggregatedPage<Item> itemPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());


        /*
        解析聚合结果集,根据聚合的类型以及字段类型,要进行强转,不然无法获取桶
         brand-是字符串类型的,聚合类型是词条类型的
        brandAgg-通过聚合名称获取聚合对象
        使用StringTerms强转的时候出现错误
         */

        StringTerms brandAgg = (StringTerms) itemPage.getAggregation("brandAgg");

        //获取桶
        List<StringTerms.Bucket> buckets = brandAgg.getBuckets();

        //遍历输出
        buckets.forEach(bucket -> {
            System.out.println("bucket.getKeyAsString() = " + bucket.getKeyAsString());
            //获取条数
            System.out.println("bucket.getDocCount() = " + bucket.getDocCount());

            //获取子聚合的map集合:key-聚合名称,value-对应的子聚合对象
            Map<String, Aggregation> stringAggregationMap = bucket.getAggregations().asMap();
            /*
            以前使用的InternalAvg强转出现转换异常
             */
            ParsedAvg price_avg = (ParsedAvg) stringAggregationMap.get("price_avg");


            System.out.println("price_avg.getValue() = " + price_avg.getValue());
        });

    }
}
