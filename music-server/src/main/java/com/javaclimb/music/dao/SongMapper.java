package com.javaclimb.music.dao;


import com.javaclimb.music.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
歌曲Dao
 */
@Repository
public interface SongMapper {
    /**
     * 增加
     * @param song
     */
    public int insert(Song song);

    /**
     * 修改
     * @param song
     */
    public int update(Song song);

    /**
     *删除
     * @param id
     */
    public int delete(Integer id);

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
     * 根据歌名模糊查询列表
     * @param name
     */
    public List<Song> likeSongOfName(String name);

    /**
     * 根据歌手id查询
     * @param singerId
     */
    public List<Song> songOfSingerId(Integer singerId);
}
