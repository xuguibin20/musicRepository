package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.domain.SongList;
import com.javaclimb.music.service.SongListService;
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


@RestController
@RequestMapping("/songList")
public class SongListController {
    @Autowired
    private SongListService songListService;

    /**
     *添加歌单
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSongList(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        //获取前端传过来的参数
        String title = request.getParameter("title").trim();//歌单标题
        String pic = "img/songListPic/mn.jpg";//默认图片，这里在后端写死先；也可以后端接收不写，在前端写死  params.append("pic", "img/songListPic/**.jpg");
//        String pic = request.getParameter("pic").trim();////歌单图片，这里在前端写死先，在后端接收；也可以前端不写，在后端写死String pic = "img/songListPic/**.jpg";
        String style = request.getParameter("style").trim();//歌单风格
        String introduction = request.getParameter("introduction").trim();//歌单简介
        //保存到歌手的对象中
        SongList songList = new SongList();
        songList.setStyle(style);
        songList.setPic(pic);
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        boolean flag = songListService.insert(songList);
        if(flag){  //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
            return jsonObject;
        }

    }


    /**
     * 修改歌单
     */
    @RequestMapping(value ="/update",method = RequestMethod.POST)
    public Object updateSongList(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();//主键
        String style = request.getParameter("style").trim();//歌单风格
        String introduction = request.getParameter("introduction").trim();//歌单简介
        String title = request.getParameter("title").trim();//歌单标题
        //保存到歌手的对象中
        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setStyle(style);
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        boolean flag = songListService.update(songList);
        if(flag){  //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
            return jsonObject;
        }

    }

    /**
     * 删除歌单
     */
    @RequestMapping(value ="/delete",method = RequestMethod.GET)
    public Object deleteSongList(HttpServletRequest request){
        //-TOOD 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
        String id = request.getParameter("id").trim();//主键
        //根据歌曲的id，找到对应的pic地址
        String Path = songListService.selectByPrimaryKey(Integer.parseInt(id)).getPic();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") ;
        //文件在后端文件夹的地址
        File dest = new File(filePath  +   Path);
        dest.delete();
        boolean flag = songListService.delete(Integer.parseInt(id));
        return flag;

    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value ="/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();//主键
        return songListService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     *查询所有歌单
     */
    @RequestMapping(value ="/allSongList",method = RequestMethod.GET)
        public Object allSongList(HttpServletRequest request){
        return songListService.allSongList();
    }

    /**
     * 根据标题模糊查询歌单
     */
    @RequestMapping(value ="/likeSongListOfTitle",method = RequestMethod.GET)
    public Object likeSongListOfTitle(HttpServletRequest request){
        String title = request.getParameter("title").trim();//歌单标题
        return songListService.likeSongListOfTitle("%"+title+"%");
    }

    /**
     * 根据标题精确查询歌单
     */
    @RequestMapping(value ="/SongListOfTitle",method = RequestMethod.GET)
    public Object SongListOfTitle(HttpServletRequest request){
        String title = request.getParameter("title").trim();//歌单标题
        return songListService.songListOfTitle(title);
    }

    /**
     * 根据风格模糊查询
     */
    @RequestMapping(value ="/songListofStyle",method = RequestMethod.GET)
    public Object likeSongListOfStyle(HttpServletRequest request){
        String style = request.getParameter("style").trim();//歌单风格
        return songListService.likeSongListOfStyle("%"+style+"%");
    }

    /**
     *更新歌单图片
     */
    @RequestMapping(value ="/updateSongListPic",method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatorFile,@RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
      if(avatorFile.isEmpty()){
          jsonObject.put(Consts.CODE,0);
          jsonObject.put(Consts.MSG,"文件上传失败");
          return jsonObject;
      }
        //-TOOD 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
        //根据歌曲的id，找到对应的pic地址
        String Path = songListService.selectByPrimaryKey(id).getPic();
        String pic = "img/songListPic/mn.jpg";
        if(Path.equals(pic) ){
            int a = -1;//什么都不做
        }else{
            //文件路径
            String filePath1 = System.getProperty("user.dir") + System.getProperty("file.separator") ;
            //文件在后端文件夹的地址
            File dest1 = new File(filePath1  +   Path);
            dest1.delete();
        }
      //文件名=当前时间（毫秒）+原来的文件名
      String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
      //文件路径
      String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+System.getProperty("file.separator")+"songListPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "img/songListPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            //没有存储权限或磁盘满了，如果更新异常，不该更新数据库
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            Boolean flag = songListService.update(songList);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("pic",storeAvatorPath);
                return jsonObject;
            }
                jsonObject.put(Consts.CODE,0);
                jsonObject.put(Consts.MSG,"上传失败");
                return jsonObject;


        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
        }finally {
            return jsonObject;
        }

    }
}
