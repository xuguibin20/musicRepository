package com.javaclimb.music.service;

import com.javaclimb.music.domain.ListSong;
import com.javaclimb.music.domain.Song;

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
     * 根据歌单id和歌曲id查询歌曲是否重复添加
     *  @param songId
     *       @param songListId
     */
    public Boolean search(Integer songId, Integer songListId);

    /**
     *删除
     * @param songId
     *  @param songListId
     */
    public Boolean delete(Integer songId,Integer songListId);

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
    public List<Song> listSongOfSongListId(Integer songListId);

}
