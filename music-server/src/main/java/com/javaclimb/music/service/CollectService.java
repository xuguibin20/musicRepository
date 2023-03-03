package com.javaclimb.music.service;

import com.javaclimb.music.domain.Collect;
import com.javaclimb.music.domain.Song;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收藏service接口
 */
public interface CollectService {
    /**
     * 增加
     * @param collect
     */
    public boolean insert(Collect collect);




    /**
     *根据用户id和歌曲id删除
     * @param userId
     * @param songId
     */
    public boolean delete(Integer userId, Integer songId);


//    /**
//     *查询所有收藏
//     */
//    public List<Collect> allCollect();

    /**
     * 根据用户id查询所有的歌曲
     * @param userId
     */
    public List<Song> collectOfUserId(Integer userId);


//
//    /**
//     * 查询某个用户是否已经收藏了某个歌曲
//     * @param userId
//     * @param songId
//     */
//    public boolean existOfSongId( Integer userId,  Integer songId);
}
