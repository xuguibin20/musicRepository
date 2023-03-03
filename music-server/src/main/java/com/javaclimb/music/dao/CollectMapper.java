package com.javaclimb.music.dao;


import com.javaclimb.music.domain.Collect;
import com.javaclimb.music.domain.ListSong;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
收藏Dao
 */
@Repository
public interface CollectMapper {
    /**
     * 增加
     * @param collect
     */
    public int insert(Collect collect);




    /**
     *根据用户id和歌曲id删除
     * @param userId
     * @param songId
     */
    public int delete(Integer songId, Integer userId);

    /**
     *查询所有收藏
     */
    public List<Collect> allCollect();

    /**
     * 根据用户id查询所有的歌曲
     * @param userId
     */
    public List<Collect> collectOfUserId(Integer userId);


    /**
     * 查询某个用户是否已经收藏了某个歌曲
     * @param userId
     * @param songId
     */
    public int existOfSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);

//    /**
//     * 查询某个用户是否已经收藏了某个歌单
//     * @param songListId
//     */
//    public List<Collect> collectOfSongListId(Integer songListId);

}
