package com.javaclimb.music.dao;


import com.javaclimb.music.domain.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
歌单Dao
 */
@Repository
public interface SongListMapper {
    /**
     * 增加
     * @param songList
     */
    public int insert(SongList songList);

    /**
     * 修改
     * @param songList
     */
    public int update(SongList songList);

    /**
     *删除
     * @param id
     */
    public int delete(Integer id);

    /**
     *根据主键查询所有歌单
     * @param id
     */
    public SongList selectByPrimaryKey(Integer id);

    /**
     *查询所有歌曲
     */
    public List<SongList> allSongList();

    /**
     * 根据标题精确查询列表
     * @param title
     */
    public List<SongList> songListOfTitle(String title);

    /**
     * 根据标题模糊查询列表
     * @param title
     */
    public List<SongList> likeSongListOfTitle(String title);

    /**
     * 根据标题模糊查询列表
     * @param style
     */
    public List<SongList> likeSongListOfStyle(String style);


}
