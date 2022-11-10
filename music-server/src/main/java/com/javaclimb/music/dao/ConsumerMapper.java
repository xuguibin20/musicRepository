package com.javaclimb.music.dao;


import com.javaclimb.music.domain.Consumer;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
前端用户Dao
 */
@Repository
public interface ConsumerMapper {
    /**
     * 增加
     * @param consumer
     */
    public int insert(Consumer consumer);

    /**
     * 修改
     * @param consumer
     */
    public int update(Consumer consumer);

    /**
     *删除
     * @param id
     */
    public int delete(Integer id);

    /**
     *根据主键查询对象
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
     * 检查注册的账号是否存在
     *  @param username
     */
    public int verifyUsername(String username);

    /**
     * 检查账号是否登录成功
     *  @param username
     */
    public int vertifyUser(String username,String password);

    /**
     * 根据用户名精确查询对象
     *  @param username
     */
    public Consumer selectByUsername(String username);
}
