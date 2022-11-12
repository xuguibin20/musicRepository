package com.javaclimb.music.service;

import com.javaclimb.music.domain.Rank;

/**
 * 评价Service接口
 */
public interface RankService {
    /**
     * 增加
     * @param rank
     */
    public boolean insert(Rank rank);

    /**
     * 查总分
     *  * @param songListId
     */
    public int selectScoreSum(Integer songListId);

    /**
     *查总评分人数
     * @param songListId
     */
    public int selectRankNum(Integer songListId);

    /**
     * 计算平均分
     */
    public int rankOfSongListId(Integer songListId);
}
