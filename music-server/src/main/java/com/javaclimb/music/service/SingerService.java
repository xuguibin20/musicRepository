package com.javaclimb.music.service;

/*
歌手service接口
 */

import com.javaclimb.music.domain.Singer;

import java.util.List;

public interface SingerService {
    /**
     * 增加
     * @param singer
     */
    public Boolean insert(Singer singer);

    /**
     * 修改
     * @param singer
     */
    public Boolean update(Singer singer);

    /**
     *删除
     * @param id
     */
    public Boolean delete(Integer id);

    /**
     *根据主键查询所有对象
     * @param id
     */
    public Singer selectByPrimaryKey(Integer id);

    /**
     *查询所有歌手
     */
    public List<Singer> allSinger();

    /**
     * 根据歌手名字模糊查询列表
     * @param name
     */
    public List<Singer> singerOfName(String name);

    /**
     * 根据性别查询
     * @param sex
     */
    public List<Singer> singerOfSex(Integer sex);
}
