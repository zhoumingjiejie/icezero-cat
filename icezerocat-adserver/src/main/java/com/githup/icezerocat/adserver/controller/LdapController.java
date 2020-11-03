package com.githup.icezerocat.adserver.controller;

import com.githup.icezerocat.adserver.service.LdapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.odm.core.ObjectDirectoryMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: ldap控制器
 * CreateDate:  2020/6/19 10:33
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("ldap")
public class LdapController {

    private final LdapTemplate ldapTemplate;
    private final LdapService ldapService;

    public LdapController(LdapService ldapService, LdapTemplate ldapTemplate) {
        this.ldapService = ldapService;
        this.ldapTemplate = ldapTemplate;
    }

    @GetMapping("say")
    public void say() {
        log.debug("hello!!!:{}", this.ldapTemplate.getContextSource());
    }

    @PostMapping("ldapAuth")
    public void ldapAuth() {
        //boolean ldapAuth = this.ldapService.ldapAuth("zero", "p@ssw0rd");
        boolean ldapAuth = this.ldapService.ldapAuth("ex_gaojun@global.cnooc.corp", "SZLtd@2018");
        log.debug("{}", ldapAuth);

        //上下文
        ContextSource contextSource = this.ldapTemplate.getContextSource();
        ObjectDirectoryMapper objectDirectoryMapper = this.ldapTemplate.getObjectDirectoryMapper();
        log.debug("{}:{}", contextSource, objectDirectoryMapper);
    }
}
