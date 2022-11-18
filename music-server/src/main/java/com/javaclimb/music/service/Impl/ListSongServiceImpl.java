package com.javaclimb.music.service.Impl;

import com.javaclimb.music.dao.ListSongMapper;
import com.javaclimb.music.dao.SongMapper;
import com.javaclimb.music.domain.ListSong;
import com.javaclimb.music.domain.Song;
import com.javaclimb.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 歌单歌曲service实现类
 */
@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;
    private SongMapper songMapper;

    public ListSongServiceImpl(SongMapper songMapper) {
        this.songMapper = songMapper;
    }

    /**
     * 增加
     * @param listSong
     */
    @Override
    public Boolean insert(ListSong listSong) {
        return listSongMapper.insert(listSong)>0;
    }

    /**
     * 修改
     * @param listSong
     */
    @Override
    public Boolean update(ListSong listSong) {
        return listSongMapper.update(listSong)>0;
    }


    /**
     * 根据歌单id和歌曲id查询歌曲是否重复添加
     *  @param songId
     *       @param songListId
     */
    public Boolean search(Integer songId, Integer songListId){
        return listSongMapper.delete(songId,songListId)>0;
    }

    /**
     * 删除
     * @param songId
       * @param songListId
     */
    @Override
    public Boolean delete(Integer songId,Integer songListId) {



        return listSongMapper.delete(songId,songListId)>0;
    }

    /**
     * 根据主键查询所有对象
     * @param id
     */
    @Override
    public ListSong selectByPrimaryKey(Integer id) {
        return listSongMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询歌单里所有歌曲
     */
    @Override
    public List<ListSong> allListSong() {
        return listSongMapper.allListSong();
    }

    /**
     * 根据歌单id查询所有的歌曲
     * @param songListId
     */
    @Override
    public List<Song> listSongOfSongListId(Integer songListId) {
        List<ListSong> listsong=  listSongMapper.listSongOfSongListId(songListId);
        List<Integer> songId = listsong.stream().map(ListSong::getSongId).collect(Collectors.toList());
        List<Song> song = new ArrayList<Song>();
        for(Integer s : songId){
            song.add(songMapper.selectByPrimaryKey(s));
        }
        return song;
    }
}
