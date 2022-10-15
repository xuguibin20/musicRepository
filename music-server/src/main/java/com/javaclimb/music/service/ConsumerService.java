package com.javaclimb.music.service;

import com.javaclimb.music.domain.Consumer;

import java.util.List;

/**
 * 前端用户Service
 */
public interface ConsumerService {
    /**
     * 增加
     * @param consumer
     */
    public Boolean insert(Consumer consumer);

    /**
     * 修改
     * @param consumer
     */
    public Boolean update(Consumer consumer);

    /**
     *删除
     * @param id
     */
    public Boolean delete(Integer id);

    /**
     *根据主键查询所有对象
     * @param id
     */
    public Consumer selectByPrimaryKey(Integer id);

    /**
     *查询所有用户
     */
    public List<Consumer> allConsumer();

    /**
     * 根据账号名字模糊查询列表
     * @param username
     */
    public List<Consumer> consumerOflikeName(String username);

    /**
     * 验证密码
     *  @param username
     *   @param password
     */
    public Boolean verifyPassword(String username,String password);
}
