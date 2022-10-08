package com.javaclimb.music.domain;

import java.io.Serializable;
import java.sql.Date;

/*
歌曲
 */
public class Song implements Serializable {
    //主键
    private Integer id;
    //歌名
    private String name;
    //歌曲图片
    private String pic;
     //简介
    private String introduction;
    //歌手id
    private Integer singerId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //歌词
    private String lyric;
    //歌曲地址
    private String url;

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }



    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", introduction='" + introduction + '\'' +
                ", singerId=" + singerId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", lyric='" + lyric + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
