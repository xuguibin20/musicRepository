package com.javaclimb.music.service.Impl;

import com.javaclimb.music.dao.ConsumerMapper;
import com.javaclimb.music.domain.Consumer;
import com.javaclimb.music.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {
@Autowired
    ConsumerMapper consumerMapper;
    /**
     * 增加
     *
     * @param consumer
     */
    @Override
    public Boolean insert(Consumer consumer) {
        return consumerMapper.insert(consumer)>0;
    }

    /**
     * 修改
     *
     * @param consumer
     */
    @Override
    public Boolean update(Consumer consumer) {
        return consumerMapper.update(consumer)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public Boolean delete(Integer id) {
        return consumerMapper.delete(id)>0;
    }

    /**
     * 根据主键查询对象
     *
     * @param id
     */
    @Override
    public Consumer selectByPrimaryKey(Integer id) {
        return consumerMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有用户
     */
    @Override
    public List<Consumer> allConsumer() {
        return consumerMapper.allConsumer();
    }

    /**
     * 根据账号名字模糊查询列表
     *
     * @param username
     */
    @Override
    public List<Consumer> consumerOflikeName(String username) {
        return consumerMapper.consumerOflikeName(username);
    }

    /**
     * 检查账号是否存在
     *
     * @param username
     */
    @Override
    public Boolean verifyUsername(String username) {
        return consumerMapper.verifyUsername(username)>0;
    }

    /**
     * 检查账号是否登录成功
     *  @param username
     *  @param password
     */
    @Override
    public Boolean vertifyUser(String username,String password){
        return consumerMapper.vertifyUser(username,password)>0;
    }

    /**
     * 根据用户名精确查询对象
     *  @param username
     */
    public Consumer selectByUsername(String username){
        return consumerMapper.selectByUsername(username);
    };
}
