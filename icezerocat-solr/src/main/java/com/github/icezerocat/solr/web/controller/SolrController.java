package com.github.icezerocat.solr.web.controller;

import com.github.icezerocat.solr.model.Md;
import com.github.icezerocat.solr.model.TGuideTheme;
import com.github.icezerocat.solr.model.TKnowledgeBase;
import com.google.common.primitives.Ints;
import com.google.gson.Gson;
import github.com.icezerocat.core.http.HttpResult;
import github.com.icezerocat.core.http.HttpStatus;
import github.com.icezerocat.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Description: solr操作控制器
 * CreateDate:  2020/11/3 9:49
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping
public class SolrController {

    @Resource
    private SolrClient solrClient;
    @Resource
    private SolrTemplate solrTemplate;

    /**
     * solrTemplate，指定collection：knowledge查询
     *
     * @return 查询结果
     */
    @GetMapping("queryAllByTemplate2")
    public HttpResult queryAllByTemplate2() {
        // 创建查询对象
        Query query = new SimpleQuery("*:*");
        // 设置分页
        query.setOffset(0L);
        query.setRows(10);
        // 执行查询
        ScoredPage<TKnowledgeBase> items = this.solrTemplate.queryForPage("knowledge", query, TKnowledgeBase.class);
        // 输出总记录数
        System.out.println("总记录数: " + items.getTotalElements());
        // 输出总页数
        System.out.println("总页数： " + items.getTotalPages());
        // 输出查询的数据
        items.getContent().forEach(System.out::println);
        return HttpResult.ok(items);
    }

    /**
     * solrTemplate，指定collection：theme
     *
     * @return 查询结果
     */
    @GetMapping("queryAllByTemplate")
    public HttpResult queryAllByTemplate() {
        // 创建查询对象
        Query query = new SimpleQuery("*:*");
        // 设置分页
        query.setOffset(0L);
        query.setRows(10);
        // 执行查询
        ScoredPage<TGuideTheme> items = this.solrTemplate.queryForPage("theme", query, TGuideTheme.class);
        // 输出总记录数
        System.out.println("总记录数: " + items.getTotalElements());
        // 输出总页数
        System.out.println("总页数： " + items.getTotalPages());
        // 输出查询的数据
        items.getContent().forEach(System.out::println);
        return HttpResult.ok(items);
    }

    @GetMapping("queryAllData")
    public HttpResult queryAllData() throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.setStart(0);
        solrQuery.setRows(20);
        QueryResponse queryResponse = this.solrClient.query(solrQuery);
        SolrDocumentList results = queryResponse.getResults();
        List<TGuideTheme> mdList = new ArrayList<>();
        for (SolrDocument solrDocument : results) {
            TGuideTheme theme = new TGuideTheme();
            theme.setThemeName(String.valueOf(solrDocument.get("THEME_NAME")));
            mdList.add(theme);
        }
        log.debug("{}", mdList);
        return HttpResult.ok(mdList);
    }

    /**
     * 根据id查询数据
     *
     * @return 查询结果
     */
    @GetMapping("queryAll")
    public HttpResult queryAll() {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("md_name:demo2");
        solrQuery.setStart(0);
        solrQuery.setRows(20);
        List<Md> mdList = new ArrayList<>();
        HttpResult.Build instance = HttpResult.Build.getInstance();
        try {
            QueryResponse queryResponse = this.solrClient.query(solrQuery);
            SolrDocumentList results = queryResponse.getResults();
            for (SolrDocument solrDocument : results) {
                Md md = new Md();
                md.setId(Optional.ofNullable(String.valueOf(solrDocument.get("id"))).map(Ints::tryParse).orElse(0));
                md.setMdName((String) solrDocument.get("md_name"));
                md.setMdCode((String) solrDocument.get("md_code"));
                mdList.add(md);
            }
            instance.setCode(HttpStatus.SC_OK).setData(mdList);
            log.debug("{}", mdList);
        } catch (SolrServerException | IOException e) {
            log.debug("找不到数据：{}", e.getMessage());
            instance.setCode(HttpStatus.SC_SERVICE_UNAVAILABLE).setMsg(e.getMessage());
            e.printStackTrace();
        }
        return instance.complete();
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 查询结果
     */
    @GetMapping("queryById2")
    public HttpResult queryById2(@RequestParam String id) {
        HttpResult.Build instance = HttpResult.Build.getInstance();
        try {
            SolrDocument solrDocument = this.solrClient.getById(id);
            Gson gson = new Gson();
            Md md = gson.fromJson(gson.toJson(solrDocument), Md.class);
            instance.setCode(HttpStatus.SC_OK).setData(md);
            log.debug("{}", md);
        } catch (SolrServerException | IOException e) {
            log.debug("找不到数据：{}", e.getMessage());
            instance.setCode(HttpStatus.SC_SERVICE_UNAVAILABLE).setMsg(e.getMessage());
            e.printStackTrace();
        }
        return instance.complete();
    }

    /**
     * 插入或更新
     *
     * @param id id
     * @return 插入结果
     */
    @GetMapping("insertAndUpdate")
    @Transactional(rollbackFor = Exception.class)
    public HttpResult insertAndUpdate(@RequestParam int id) {
        HttpResult.Build httpResultBuild = HttpResult.Build.getInstance();
        Md md = new Md();
        md.setId(id);
        md.setMdName("demo" + id);
        md.setMdCode("insertAndUpdate:".concat(DateUtil.formatDateTime(new Date())));
        httpResultBuild.setCode(HttpStatus.SC_OK).setData(md);
        try {
            UpdateResponse updateResponse = this.solrClient.addBean(md, 1000);
            if (updateResponse.getStatus() != 0) {
                log.error("update solr document failed");
                this.solrClient.rollback();
                httpResultBuild.setCode(HttpStatus.SC_SERVICE_UNAVAILABLE).setMsg("insert user to solr failed");
            }
        } catch (IOException | SolrServerException e) {
            log.error("插入或更新数据出错：{}", e.getMessage());
            httpResultBuild.setCode(HttpStatus.SC_SERVICE_UNAVAILABLE).setMsg(e.getMessage());
            e.printStackTrace();
        }
        return httpResultBuild.complete();
    }

    /**
     * 根据id删除数据
     *
     * @param id id
     * @return 删除结果
     */
    @GetMapping("deleteById")
    @Transactional(rollbackFor = Exception.class)
    public HttpResult deleteById(String id) {
        HttpResult.Build instance = HttpResult.Build.getInstance();
        instance.setCode(HttpStatus.SC_OK);
        try {
            UpdateResponse updateResponse = this.solrClient.deleteById(id, 1000);
            if (updateResponse.getStatus() != 0) {
                log.error("delete solr document failed, id = ".concat(id));
                this.solrClient.rollback();
                instance.setCode(HttpStatus.SC_SERVICE_UNAVAILABLE).setMsg("delete user to solr failed, id = ".concat(id));
            }
        } catch (SolrServerException | IOException e) {
            instance.setCode(HttpStatus.SC_SERVICE_UNAVAILABLE).setMsg(e.getMessage());
            e.printStackTrace();
        }
        return instance.complete();
    }
}
