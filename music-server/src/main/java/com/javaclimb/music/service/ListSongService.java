package com.javaclimb.music.service;

import com.javaclimb.music.domain.ListSong;

import java.util.List;

/**
 * 歌单歌曲Service
 */
public interface ListSongService {

    /**
     * 增加
     * @param listSong
     */
    public Boolean insert(ListSong listSong);

    /**
     * 修改
     * @param listSong
     */
    public Boolean update(ListSong listSong);

    /**
     *删除
     * @param id
     */
    public Boolean delete(Integer id);

    /**
     *根据主键查询所有对象
     * @param id
     */
    public ListSong selectByPrimaryKey(Integer id);

    /**
     *查询歌单里所有歌曲
     */
    public List<ListSong> allListSong();


    /**
     * 根据歌单id查询所有的歌曲
     * @param songListId
     */
    public List<ListSong> listSongOfSongListId(Integer songListId);

}
