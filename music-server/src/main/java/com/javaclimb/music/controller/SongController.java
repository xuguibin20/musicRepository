package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;


import com.javaclimb.music.domain.Song;
import com.javaclimb.music.service.SongService;
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
 * 歌曲管理Controller
 */
@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;


    /**
     * 添加歌曲
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request, @RequestParam("file") MultipartFile mpFile) {
        JSONObject jsonObject = new JSONObject();
        //获取前端传过来的参数
        String name = request.getParameter("name").trim();//歌名
        String singerId = request.getParameter("singerId").trim();//所属歌手id
//        String pic = request.getParameter("pic").trim();//歌曲图片，在前端写死先,后端接收的方式
        String pic = "img/songPic/tubiao.jpg";//默认图片，这里在后端写死先；也可以后端接收不写，在前端写死  params.append("pic", "img/songPic/**.jpg");
        String introduction = request.getParameter("introduction").trim();//简介
        String lyric = request.getParameter("lyric").trim();//歌词
        //上传歌曲文件，即song类里的url
        if (mpFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "歌曲上传失败");
            return jsonObject;
        }
        //文件名=当前时间（到毫秒）+原来的文件名
        String fileName =System.currentTimeMillis()+ mpFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();

        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeUrlPath = "song/" + fileName;
        try {
            mpFile.transferTo(dest);
            //没有存储权限或磁盘满了，如果更新异常，不该更新数据库
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singerId));

            song.setName(name);//////////////////////////////////////
            song.setIntroduction(introduction);
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            Boolean flag = songService.insert(song);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "保存成功");
                jsonObject.put("avator", storeUrlPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "保存失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "保存失败" + e.getMessage());
        } finally {
            return jsonObject;
        }
    }



    /**
     * 根据歌手id查询歌曲
     */
    @RequestMapping(value = "/singer/detail", method = RequestMethod.GET)
    public Object songOfSingerId(HttpServletRequest request) {
        String singerId = request.getParameter("singerId").trim();
        return songService.songOfSingerId(Integer.parseInt(singerId));

    }

    /**
     * 根据歌单id查询该歌单下的所有歌曲
     */
    @RequestMapping(value = "/songList/detail", method = RequestMethod.GET)
    public Object listSongOfSongListId(HttpServletRequest request) {
        String singerId = request.getParameter("songListId").trim();
        return songService.songOfSingerId(Integer.parseInt(singerId));

    }

    /**
     * 根据歌曲id查询歌曲
     */
    @RequestMapping(value = "/songOfSongId", method = RequestMethod.GET)
    public Object songOfSongId(HttpServletRequest request) {
        String songId = request.getParameter("songId").trim();
        return songService.selectByPrimaryKey(Integer.parseInt(songId));

    }


    /**
     * 根据歌名精确查询
     */
    @RequestMapping(value = "/songOfName", method = RequestMethod.GET)
    public Object songOfName(HttpServletRequest request) {
        String songName = request.getParameter("songName").trim();;
        return songService.songOfName(songName);
    }

    /**
     * 根据歌名模糊查询歌曲
     */
    @RequestMapping(value = "/likeSongOfName", method = RequestMethod.GET)
    public Object likeSongOfName(HttpServletRequest request) {
        String songName = request.getParameter("songName");
        return songService.likeSongOfName(songName);
    }

    /**
     * 编辑歌曲信息
     */
    @RequestMapping(value = "/updateSongMsg", method = RequestMethod.POST)
    public Object updateSongMsg(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();//主键
        String name = request.getParameter("name").trim();//歌名
        String introduction = request.getParameter("introduction").trim();//专辑
        String lyric = request.getParameter("lyric").trim();//歌词
        //保存到歌曲的对象中
        Song song = new Song();
        song.setName(name);
        song.setId(Integer.parseInt(id));
        song.setLyric(lyric);
        song.setIntroduction(introduction);
        boolean flag = songService.update(song);
        if (flag) {  //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "修改失败");
            return jsonObject;
        }

    }

    /**
     * 查询所有歌曲
     */
    @RequestMapping(value = "/allSong", method = RequestMethod.GET)
    public Object allSong(HttpServletRequest request) {
        return songService.allSong();
    }

    ;

    /**
     * 删除歌曲
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteSong(HttpServletRequest request) {
      //-TOOD 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
        String id = request.getParameter("id").trim();//主键
        //根据歌曲的id，找到对应的url地址
        String Path = songService.selectByPrimaryKey(Integer.parseInt(id)).getUrl();
        String Path1 = songService.selectByPrimaryKey(Integer.parseInt(id)).getPic();
        //文件路径
      String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") ;
      //文件在后端文件夹的地址
        File dest = new File(filePath  +   Path);
        File dest1 = new File(filePath  +   Path1);
        dest.delete();
        dest1.delete();
        boolean flag = songService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value ="/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();//主键
        return songService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     *更新歌曲图片
     */
    @RequestMapping(value ="/updateSongPic",method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file") MultipartFile avatorFile,@RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //-TOOD 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
        //根据歌曲的id，找到对应的pic地址
        String Path = songService.selectByPrimaryKey(id).getPic();
        String pic = "img/songPic/tubiao.jpg";
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
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+System.getProperty("file.separator")+"songPic";
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "img/songPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            //没有存储权限或磁盘满了，如果更新异常，不该更新数据库
            Song song = new Song();
            song.setId(id);
            song.setPic(storeAvatorPath);
            Boolean flag = songService.update(song);
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

    /**
     *更新歌曲
     */
    @RequestMapping(value ="/updateSong",method = RequestMethod.POST)
    public Object updateSong(@RequestParam("file") MultipartFile avatorFile,@RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //-TOOD 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
        //根据歌曲的id，找到对应的url地址
        String Path = songService.selectByPrimaryKey(id).getUrl();
        //文件路径
        String filePath1 = System.getProperty("user.dir") + System.getProperty("file.separator") ;
        //文件在后端文件夹的地址
        File dest1 = new File(filePath1  +   Path);
        dest1.delete();
        //文件名=当前时间（毫秒）+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "song/"+fileName;
        try {
            avatorFile.transferTo(dest);
            //没有存储权限或磁盘满了，如果更新异常，不该更新数据库
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeAvatorPath);
            Boolean flag = songService.update(song);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("avator",storeAvatorPath);
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
