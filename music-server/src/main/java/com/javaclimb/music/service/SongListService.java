package com.javaclimb.music.service;

import com.javaclimb.music.domain.SongList;

import java.util.List;

/**
 * 歌单Service
 */
public interface SongListService {
    /**
     * 增加
     * @param songList
     */
    public Boolean insert(SongList songList);

    /**
     * 修改
     * @param songList
     */
    public Boolean update(SongList songList);

    /**
     *删除
     * @param id
     */
    public Boolean delete(Integer id);

    /**
     *根据主键查询所有对象
     * @param id
     */
    public SongList selectByPrimaryKey(Integer id);

    /**
     *查询所有歌单
     */
    public List<SongList> allSongList();

    /**
     * 根据标题精确查询列表
     * @param title
     */
    public List<SongList> songListOfTitle(String title);

    /**
     * 根据标题模糊查询歌曲
     * @param title
     */
    public List<SongList> likeSongListOfTitle(String title);

    /**
     * 根据风格查询
     * @param style
     */
    public List<SongList> likeSongListOfStyle(String style);
}
