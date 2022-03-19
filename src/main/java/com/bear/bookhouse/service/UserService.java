package com.bear.bookhouse.service;

import com.bear.bookhouse.pojo.User;

/**
 * @author Spring-_-Bear
 * @datetime 2022/3/16 19:58
 */
public interface UserService {
    /**
     * 保存用户信息
     *
     * @param user User
     * @return true - 保存成功
     */
    boolean saveUser(User user);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return User or null
     */
    User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return User or null
     */
    User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 验证邮箱地址是否已存在
     *
     * @param email 邮箱地址
     * @return User or null
     */
    User queryUserByEmail(String email);

    /**
     * 通过用户邮箱信息更新用户密码
     *
     * @param email    邮箱
     * @param password 密码
     * @return true - 更新成功
     */
    boolean updateUserPasswordByEmail(String email, String password);
}
