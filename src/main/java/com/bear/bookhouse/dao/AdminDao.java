package com.bear.bookhouse.dao;

import com.bear.bookhouse.pojo.Admin;

/**
 * @author Spring-_-Bear
 * @datetime 2022/3/27 11:36
 */
public interface AdminDao {
    /**
     * 通过用户名、密码查询管理员信息
     *
     * @param username 用户名
     * @param password 密码
     * @return Admin or null
     */
    Admin getAdminByUsernameAndPassword(String username, String password);
}
