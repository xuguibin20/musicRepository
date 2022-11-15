package com.javaclimb.music.service;

import com.javaclimb.music.domain.Collect;
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
     *删除
     * @param songId
     */
    public boolean delete(Integer songId);

    /**
     *查询所有收藏
     */
    public List<Collect> allCollect();

    /**
     * 查询某个用户的收藏列表
     * @param userId
     */
    public List<Collect> collectOfUserId(Integer userId);

    /**
     * 查询某个用户是否已经收藏了某个歌曲
     * @param userId
     * @param songId
     */
    public boolean existOfSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);
}
