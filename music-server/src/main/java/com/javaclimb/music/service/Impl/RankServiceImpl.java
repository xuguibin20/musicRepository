package com.javaclimb.music.service.Impl;

import com.javaclimb.music.dao.RankMapper;
import com.javaclimb.music.domain.Rank;
import com.javaclimb.music.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 评价Service实现类
 */
@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankMapper rankMapper;
    /**
     * 增加
     * @param rank
     */
    public boolean insert(Rank rank){
        return rankMapper.insert(rank)>0;
    }

    /**
     * 查总分
     *  * @param songListId
     */
    public int selectScoreSum(Integer songListId){
        return rankMapper.selectScoreSum(songListId);
    }

    /**
     *查总评分人数
     * @param songListId
     */
    public int selectRankNum(Integer songListId){
        return rankMapper.selectRankNum(songListId);
    }

    /**
     * 计算平均分
     */
    public int rankOfSongListId(Integer songListId){
        int rankNum  = rankMapper.selectRankNum(songListId);
        if(rankNum == 0){
            return 5;
        }
        return rankMapper.selectScoreSum(songListId)/rankNum;
    }
}
