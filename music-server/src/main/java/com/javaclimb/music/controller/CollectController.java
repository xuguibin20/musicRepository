package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.domain.Collect;
import com.javaclimb.music.service.CollectService;
import com.javaclimb.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 收藏Controller层
 */
@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;



    /**
     * 删除收藏
     */
    @RequestMapping(value ="/delete",method = RequestMethod.POST)
    public Object deleteCollect(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String songId = request.getParameter("songId");//歌曲id
        String userId = request.getParameter("userId");//用户id
        boolean flag = collectService.delete(Integer.parseInt(songId),Integer.parseInt(userId));
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"取消成功");
            return jsonObject;
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"取消失败");
            return jsonObject;
        }


    }

    /**
     * 根据用户id查询歌曲
     */
    @RequestMapping(value = "/collectOfUserId", method = RequestMethod.GET)
    public Object collectOfUserId(HttpServletRequest request) {
        String userId = request.getParameter("userId").trim();//用户id
        return collectService.collectOfUserId(Integer.parseInt(userId));

    }
//
//    /**
//     *查询所有收藏
//     */
//    @RequestMapping(value ="/allCollect",method = RequestMethod.GET)
//        public Object allCollect(HttpServletRequest request){
//        return collectService.allCollect();
//    }


    }


