package com.javaclimb.music.service;

import com.javaclimb.music.domain.Comment;

import java.util.List;

/**
 * 评论service接口
 */
public interface CommentService {

    /**
     * 增加
     * @param comment
     */
    public boolean insert(Comment comment);

    /**
     * 修改
     * @param comment
     */
    public boolean update(Comment comment);

    /**
     *删除
     * @param id
     */
    public boolean delete(Integer id);

    /**
     *根据主键查询所有对象
     * @param id
     */
    public Comment selectByPrimaryKey(Integer id);

    /**
     *查询所有评论
     */
    public List<Comment> allComment();

    /**
     * 查询某个歌曲下的所有评论
     * @param songId
     */
    public List<Comment> commentOfSongId(Integer songId);

    /**
     * 查询某个歌单下的所有评论
     * @param songListId
     */
    public List<Comment> commentOfSongListId(Integer songListId);
}
