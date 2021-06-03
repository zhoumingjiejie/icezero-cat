package com.github.icezerocat.study.mp.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.com.icezerocat.ap.TKnowledgeQueryLog;
import github.com.icezerocat.component.db.service.DbService;
import github.com.icezerocat.component.mp.common.mybatisplus.NoahServiceImpl;
import github.com.icezerocat.component.mp.model.MpModel;
import github.com.icezerocat.component.mp.service.BaseMpBuildService;
import github.com.icezerocat.component.mp.service.MpTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;

/**
 * Description: mp控制器
 * CreateDate:  2021/4/30 15:31
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class MpController {


    private Logger logger = LoggerFactory.getLogger(MpController.class);

    private final DbService dbService;
    private final MpTableService mpTableService;
    private final BaseMpBuildService baseMpBuildService;

    @GetMapping("tableInfo")
    public void tableInfo() {
        logger.info("{}", this.dbService.getTableInfo());
    }

    @PostMapping("body")
    public String body(@RequestBody MpModel value) {
        TKnowledgeQueryLog tKnowledgeQueryLog = new TKnowledgeQueryLog();
        tKnowledgeQueryLog.setKnowledgeId(1234567);
        tKnowledgeQueryLog.setQueryTime(new Date());

        NoahServiceImpl<BaseMapper<Object>, Object> noahService = this.baseMpBuildService.newInstance(tKnowledgeQueryLog);
        //NoahServiceImpl<BaseMapper<Object>, Object> a = this.baseMpBuildService.newInstance("t_knowledge_base");
        //NoahServiceImpl<BaseMapper<Object>, Object> b = this.baseMpBuildService.newInstance("t_knowledge_query_log");
       /*List objects = a.list();
        log.warn("{}", a.list());*/
        logger.info("{}", noahService.saveOrUpdateBatch(Collections.singletonList(tKnowledgeQueryLog)));
        return JSONObject.toJSONString(tKnowledgeQueryLog);
    }
}
