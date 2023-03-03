package com.javaclimb.music.service.Impl;

import com.javaclimb.music.dao.CollectMapper;


import com.javaclimb.music.dao.SongMapper;
import com.javaclimb.music.domain.Collect;


import com.javaclimb.music.service.CollectService;
import com.javaclimb.music.domain.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;
   private SongMapper songMapper;

    public CollectServiceImpl(SongMapper songMapper) {
        this.songMapper = songMapper;
    }

    /**
     * 增加
     *
     * @param collect
     */
    @Override
    public boolean insert(Collect collect) {
        return collectMapper.insert(collect)>0;
    }



    /**
     *根据用户id和歌曲id删除
     * @param userId
     * @param songId
     */
    @Override
    public boolean delete(Integer userId, Integer songId) {
        return collectMapper.delete(userId,songId)>0;
    }

//    /**
//     * 查询所有收藏
//     */
//    @Override
//    public List<Collect> allCollect() {
//        return collectMapper.allCollect();
//    }
//
//

    /**
     * 根据歌单id查询所有的歌曲
     * @param userId
     */
    @Override
    public List<Song> collectOfUserId(Integer userId) {
        List<Collect> collects = collectMapper.collectOfUserId(userId);
        List<Integer> songId = collects.stream().map(Collect::getSongId).collect(Collectors.toList());
        List<Song> song = new ArrayList<Song>();
        for(Integer s : songId){
            song.add(songMapper.selectByPrimaryKey(s));
        }
        return song;
    }
//    /**
//     * 查询某个用户是否已经收藏了某个歌曲
//     *
//     * @param userId
//     * @param songId
//     */
//    @Override
//    public boolean existOfSongId(Integer userId, Integer songId) {
//        return collectMapper.existOfSongId(userId,songId)>0;
//    }
}
