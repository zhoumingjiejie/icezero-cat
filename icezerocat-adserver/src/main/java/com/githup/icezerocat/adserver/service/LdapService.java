package com.githup.icezerocat.adserver.service;

/**
 * Description: ldap操作
 * CreateDate:  2020/6/19 10:15
 *
 * @author zero
 * @version 1.0
 */
public interface LdapService {
    /**
     * 域身份验证
     *
     * @param username 用户名
     * @param passWord 密码
     * @return 返回结果
     */
    boolean ldapAuth(String username, String passWord);
}
