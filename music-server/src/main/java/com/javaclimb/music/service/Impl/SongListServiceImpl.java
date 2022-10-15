package com.javaclimb.music.service.Impl;

import com.javaclimb.music.dao.SongListMapper;
import com.javaclimb.music.domain.SongList;
import com.javaclimb.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌单Service实现类
 */
@Service
public class SongListServiceImpl implements SongListService {
@Autowired
private SongListMapper songListMapper;
    /**
     * 增加
     *
     * @param songList
     */
    @Override
    public Boolean insert(SongList songList) {
        return songListMapper.insert(songList)>0;
    }

    /**
     * 修改
     *
     * @param songList
     */
    @Override
    public Boolean update(SongList songList) {
        return songListMapper.update(songList)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public Boolean delete(Integer id) {
        return songListMapper.delete(id)>0;
    }

    /**
     * 根据主键查询所有对象
     *
     * @param id
     */
    @Override
    public SongList selectByPrimaryKey(Integer id) {
        return songListMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌单
     */
    @Override
    public List<SongList> allSongList() {
        return songListMapper.allSongList();
    }

    /**
     * 根据标题精确查询列表
     *
     * @param title
     */
    @Override
    public List<SongList> songListOfTitle(String title) {
        return songListMapper.songListOfTitle(title);
    }

    /**
     * 根据标题模糊查询歌曲
     *
     * @param title
     */
    @Override
    public List<SongList> likeSongListOfTitle(String title) {
        return songListMapper.likeSongListOfTitle("%"+title+"%");
    }

    /**
     * 根据风格查询
     *
     * @param style
     */
    @Override
    public List<SongList> likeSongListOfStyle(String style) {
        return songListMapper.likeSongListOfStyle("%"+style+"%");
    }
}
