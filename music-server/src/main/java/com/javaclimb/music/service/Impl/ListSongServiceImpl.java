package com.javaclimb.music.service.Impl;

import com.javaclimb.music.dao.ListSongMapper;
import com.javaclimb.music.domain.ListSong;
import com.javaclimb.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌单歌曲service实现类
 */
@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    ListSongMapper listSongMapper;
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
     * 删除
     * @param id
     */
    @Override
    public Boolean delete(Integer id) {
        return listSongMapper.delete(id)>0;
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
    public List<ListSong> listSongOfSongListId(Integer songListId) {
        return listSongMapper.listSongOfSongListId(songListId);
    }
}
