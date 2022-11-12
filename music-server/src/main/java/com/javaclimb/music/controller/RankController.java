package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.domain.Rank;
import com.javaclimb.music.service.RankService;
import com.javaclimb.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 评价Controller
 */
@RestController
public class RankController {
    @Autowired
    private RankService rankService;

    /**
     * 新增评价
     */
    @RequestMapping(value = "/rank/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String songListId = request.getParameter("songListId").trim();
        String consumerId = request.getParameter("consumerId").trim();
        String score = request.getParameter("score").trim();
        Rank rank = new Rank();
        rank.setSongListId(Integer.parseInt(songListId));
        rank.setConsumerId(Integer.parseInt(consumerId));
        rank.setScore(Integer.parseInt(score));
        boolean flag = rankService.insert(rank);
        if (flag) {
            jsonObject.put("code", 1);
            jsonObject.put(Consts.MSG, "评价成功");

            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "评价失败");
        return jsonObject;
    }

    /**
     * 计算平均分
     */
    @RequestMapping(value = "/rank", method = RequestMethod.GET)
    public Object rankOfSongListId(HttpServletRequest request) {
        String songListId = request.getParameter("songListId").trim();
        return rankService.rankOfSongListId(Integer.parseInt(songListId));
    }
}
