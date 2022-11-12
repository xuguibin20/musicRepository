package com.javaclimb.music.dao;


import com.javaclimb.music.domain.Rank;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
评价Dao
 */
@Repository
public interface RankMapper {
    /**
     * 增加
     * @param rank
     */
    public int insert(Rank rank);

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


}
