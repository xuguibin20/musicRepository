package com.javaclimb.music.dao;


import com.javaclimb.music.domain.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
歌手Dao
 */
@Repository
public interface SingerMapper {
    /**
     * 增加
     * @param Singer
     */
    public int insert(Singer Singer);

    /**
     * 修改
     * @param Singer
     */
    public int update(Singer Singer);

    /**
     *删除
     * @param id
     */
    public int delete(Integer id);

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
