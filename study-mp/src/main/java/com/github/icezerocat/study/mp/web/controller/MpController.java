package com.github.icezerocat.study.mp.web.controller;

import github.com.icezerocat.component.db.service.DbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: mp控制器
 * CreateDate:  2021/4/30 15:31
 *
 * @author zero
 * @version 1.0
 */
@RestController
public class MpController {

    private Logger logger = LoggerFactory.getLogger(MpController.class);

    private final DbService dbService;

    public MpController(DbService dbService) {
        this.dbService = dbService;
    }

    @GetMapping("tableInfo")
    public void tableInfo() {
        logger.info("{}", this.dbService.getTableInfo());
    }
}
