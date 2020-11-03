package com.githup.icezerocat.adserver.service.impl;

import com.githup.icezerocat.adserver.service.LdapService;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

/**
 * Description: ldap
 * CreateDate:  2020/6/19 10:18
 *
 * @author zero
 * @version 1.0
 */
@Service
public class LdapServiceImpl implements LdapService {

    private final LdapTemplate ldapTemplate;

    public LdapServiceImpl(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public boolean ldapAuth(String username, String passWord) {
        EqualsFilter filter = new EqualsFilter("sAMAccountName", username);
        this.ldapTemplate.setIgnorePartialResultException(true);
        return ldapTemplate.authenticate("", filter.toString(), passWord);
    }
}
