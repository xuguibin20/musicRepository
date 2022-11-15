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
     *添加收藏
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addCollect(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        //获取前端传过来的参数
        String userId = request.getParameter("userId");//用户id
        String type = request.getParameter("type"); //收藏类型（0歌曲1歌单）
        String songId = request.getParameter("songId");;//歌曲id
      if(songId==null||songId.equals("")){
          jsonObject.put(Consts.CODE,0);
          jsonObject.put(Consts.MSG,"收藏的歌曲为空");
          return jsonObject;
      }
      if(collectService.existOfSongId(Integer.parseInt(userId),Integer.parseInt(songId))){
          jsonObject.put(Consts.CODE,2);
          jsonObject.put(Consts.MSG,"已收藏");
          return jsonObject;
      }
        //保存到收藏的对象中
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setType(new Byte(type));
        collect.setSongId(Integer.parseInt(songId));
        boolean flag = collectService.insert(collect);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"收藏成功");
            return jsonObject;
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"收藏失败");
            return jsonObject;
        }

    }

    /**
     * 删除收藏
     */
    @RequestMapping(value ="/delete",method = RequestMethod.GET)
    public Object deleteCollect(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String songId = request.getParameter("songId");//歌曲id
        boolean flag = collectService.delete(Integer.parseInt(songId));
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
     * 查询某个用户的收藏列表
     *
     */
    @RequestMapping(value ="/collectOfUserId",method = RequestMethod.GET)
    public Object collectOfUserId(HttpServletRequest request){
        String userId = request.getParameter("userId").trim();//用户id
        return collectService.collectOfUserId(Integer.parseInt(userId));
    }

    /**
     *查询所有收藏
     */
    @RequestMapping(value ="/allCollect",method = RequestMethod.GET)
        public Object allCollect(HttpServletRequest request){
        return collectService.allCollect();
    }


    }


