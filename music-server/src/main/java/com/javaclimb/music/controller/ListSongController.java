package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.domain.ListSong;
import com.javaclimb.music.service.ListSongService;
import com.javaclimb.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


/**
 *歌单歌曲管理Controller
 */
@RestController
@RequestMapping("/listSong")
public class ListSongController {
    @Autowired
    private ListSongService listSongService;


    /**
     * 添加歌单里的歌曲
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addListSong(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        //获取前端传过来的参数
        String songId = request.getParameter("songId").trim();//歌曲id
        String songListId = request.getParameter("songListId").trim();//歌单id
            ListSong listSong = new ListSong();
            listSong.setSongId(Integer.parseInt(songId));
            listSong.setSongListId(Integer.parseInt(songListId));
            Boolean flag = listSongService.insert(listSong);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "保存成功");
                return jsonObject;
            }else{

            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "保存失败");
            return jsonObject;
        }
    };

    /**
     * 根据歌单id和歌曲id查询歌曲是否重复添加
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Object searchListSong(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String songId = request.getParameter("songId").trim();//歌曲id
        String songListId = request.getParameter("songListId").trim();//歌单id

        boolean flag = listSongService.search(Integer.parseInt(songId), Integer.parseInt(songListId));

        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "重复添加");
            return jsonObject;
        } else {

            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        }
    }
//
//    /**
//     * 根据歌名精确查询
//     */
//    @RequestMapping(value = "/listSongOfName", method = RequestMethod.GET)
//    public Object listSongOfName(HttpServletRequest request) {
//        String listSongName = request.getParameter("listSongName");
//        return listSongService.listSongOfName(listSongName);
//    }
//
//    /**
//     * 根据歌名模糊查询歌曲
//     */
//    @RequestMapping(value = "/likeListSongOfName", method = RequestMethod.GET)
//    public Object likeListSongOfName(HttpServletRequest request) {
//        String listSongName = request.getParameter("listSongName");
//        return listSongService.likeListSongOfName(listSongName);
//    }

    /**
     * 根据歌单id查询歌曲
     */
    @RequestMapping(value = "/listSongOfSongListId", method = RequestMethod.GET)
    public Object listSongOfSongListId(HttpServletRequest request) {
        String songListId = request.getParameter("songListId").trim();
        return listSongService.listSongOfSongListId(Integer.parseInt(songListId));

    }



    /**
     * 查询所有歌曲
     */
    @RequestMapping(value = "/allListSong", method = RequestMethod.GET)
    public Object allListSong(HttpServletRequest request) {
        return listSongService.allListSong();
    }



    /**
     * 删除歌单里的歌曲
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object deleteListSong(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String songId = request.getParameter("songId").trim();//歌曲id
        String songListId = request.getParameter("songListId").trim();//歌单id

        boolean flag = listSongService.delete(Integer.parseInt(songId),Integer.parseInt(songListId));

        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "删除成功");
            return jsonObject;
        }else{

            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "删除失败");
            return jsonObject;
        }

    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value ="/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();//主键
        return listSongService.selectByPrimaryKey(Integer.parseInt(id));
    }



}
