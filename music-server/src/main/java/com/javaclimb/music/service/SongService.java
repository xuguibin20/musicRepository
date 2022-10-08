package com.javaclimb.music.service;

import com.javaclimb.music.domain.Song;

import java.util.List;

/**
 * 歌曲Service
 */
public interface SongService {
    /**
     * 增加
     * @param song
     */
    public Boolean insert(Song song);

    /**
     * 修改
     * @param song
     */
    public Boolean update(Song song);

    /**
     *删除
     * @param id
     */
    public Boolean delete(Integer id);

    /**
     *根据主键查询所有对象
     * @param id
     */
    public Song selectByPrimaryKey(Integer id);

    /**
     *查询所有歌曲
     */
    public List<Song> allSong();

    /**
     * 根据歌名精确查询列表
     * @param name
     */
    public List<Song> songOfName(String name);

    /**
     * 根据歌名模糊查询歌曲
     * @param name
     */
    public List<Song> likeSongOfName(String name);

    /**
     * 根据性别查询
     * @param singerId
     */
    public List<Song> songOfSingerId(Integer singerId);
}
