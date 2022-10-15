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
    public Object addListSong(HttpServletRequest request, @RequestParam("file") MultipartFile mpFile) {
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
     * 编辑歌曲信息
     */
//    @RequestMapping(value = "/updateListSongMsg", method = RequestMethod.POST)
//    public Object updateListSongMsg(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        String id = request.getParameter("id").trim();//主键
//        String name = request.getParameter("name").trim();//歌名
//        String introduction = request.getParameter("introduction").trim();//专辑
//        String lyric = request.getParameter("lyric").trim();//歌词
//        //保存到歌曲的对象中
//        ListSong listSong = new ListSong();
//        listSong.setName(name);
//        listSong.setId(Integer.parseInt(id));
//        listSong.setLyric(lyric);
//        listSong.setIntroduction(introduction);
//        boolean flag = listSongService.update(listSong);
//        if (flag) {  //保存成功
//            jsonObject.put(Consts.CODE, 1);
//            jsonObject.put(Consts.MSG, "修改成功");
//            return jsonObject;
//        } else {
//            jsonObject.put(Consts.CODE, 0);
//            jsonObject.put(Consts.MSG, "修改失败");
//            return jsonObject;
//        }
//
//    }

    /**
     * 查询所有歌曲
     */
    @RequestMapping(value = "/allListSong", method = RequestMethod.GET)
    public Object allListSong(HttpServletRequest request) {
        return listSongService.allListSong();
    }

    ;

    /**
     * 删除歌曲
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteListSong(HttpServletRequest request) {
      //-TOOD 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
        String id = request.getParameter("id").trim();//主键
        //根据歌曲的id，找到对应的url地址
//        String Path = listSongService.selectByPrimaryKey(Integer.parseInt(id)).getUrl();
//        String Path1 = listSongService.selectByPrimaryKey(Integer.parseInt(id)).getPic();
//        //文件路径
//      String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") ;
//      //文件在后端文件夹的地址
//        File dest = new File(filePath  +   Path);
//        File dest1 = new File(filePath  +   Path1);
//        dest.delete();
//        dest1.delete();
        boolean flag = listSongService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value ="/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();//主键
        return listSongService.selectByPrimaryKey(Integer.parseInt(id));
    }

//    /**
//     *更新歌曲图片
//     */
//    @RequestMapping(value ="/updateListSongPic",method = RequestMethod.POST)
//    public Object updateListSongPic(@RequestParam("file") MultipartFile avatorFile,@RequestParam("id")int id){
//        JSONObject jsonObject = new JSONObject();
//        if(avatorFile.isEmpty()){
//            jsonObject.put(Consts.CODE,0);
//            jsonObject.put(Consts.MSG,"文件上传失败");
//            return jsonObject;
//        }
//        //-TOOD 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
//        //根据歌曲的id，找到对应的pic地址
//        String Path = listSongService.selectByPrimaryKey(id).getPic();
//        String pic = "img/listSongPic/tubiao.jpg";
//        if(Path.equals(pic) ){
//          int a = -1;//什么都不做
//        }else{
//            //文件路径
//            String filePath1 = System.getProperty("user.dir") + System.getProperty("file.separator") ;
//            //文件在后端文件夹的地址
//            File dest1 = new File(filePath1  +   Path);
//            dest1.delete();
//        }
//        //文件名=当前时间（毫秒）+原来的文件名
//        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
//        //文件路径
//        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+System.getProperty("file.separator")+"listSongPic";
//        //实际的文件地址
//        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
//        //存储到数据库里的相对文件地址
//        String storeAvatorPath = "img/listSongPic/"+fileName;
//        try {
//            avatorFile.transferTo(dest);
//            //没有存储权限或磁盘满了，如果更新异常，不该更新数据库
//            ListSong listSong = new ListSong();
//            listSong.setId(id);
//            listSong.setPic(storeAvatorPath);
//            Boolean flag = listSongService.update(listSong);
//            if(flag){
//                jsonObject.put(Consts.CODE,1);
//                jsonObject.put(Consts.MSG,"上传成功");
//                jsonObject.put("pic",storeAvatorPath);
//                return jsonObject;
//            }
//            jsonObject.put(Consts.CODE,0);
//            jsonObject.put(Consts.MSG,"上传失败");
//            return jsonObject;
//
//
//        } catch (IOException e) {
//            jsonObject.put(Consts.CODE,0);
//            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
//        }finally {
//            return jsonObject;
//        }
//
//    }

//    /**
//     *更新歌曲
//     */
//    @RequestMapping(value ="/updateListSong",method = RequestMethod.POST)
//    public Object updateListSong(@RequestParam("file") MultipartFile avatorFile,@RequestParam("id")int id){
//        JSONObject jsonObject = new JSONObject();
//        if(avatorFile.isEmpty()){
//            jsonObject.put(Consts.CODE,0);
//            jsonObject.put(Consts.MSG,"文件上传失败");
//            return jsonObject;
//        }
//        //-TOOD 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
//        //根据歌曲的id，找到对应的url地址
//        String Path = listSongService.selectByPrimaryKey(id).getUrl();
//        //文件路径
//        String filePath1 = System.getProperty("user.dir") + System.getProperty("file.separator") ;
//        //文件在后端文件夹的地址
//        File dest1 = new File(filePath1  +   Path);
//        dest1.delete();
//        //文件名=当前时间（毫秒）+原来的文件名
//        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
//        //文件路径
//        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"listSong";
//        //实际的文件地址
//        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
//        //存储到数据库里的相对文件地址
//        String storeAvatorPath = "listSong/"+fileName;
//        try {
//            avatorFile.transferTo(dest);
//            //没有存储权限或磁盘满了，如果更新异常，不该更新数据库
//            ListSong listSong = new ListSong();
//            listSong.setId(id);
//            listSong.setUrl(storeAvatorPath);
//            Boolean flag = listSongService.update(listSong);
//            if(flag){
//                jsonObject.put(Consts.CODE,1);
//                jsonObject.put(Consts.MSG,"上传成功");
//                jsonObject.put("avator",storeAvatorPath);
//                return jsonObject;
//            }
//            jsonObject.put(Consts.CODE,0);
//            jsonObject.put(Consts.MSG,"上传失败");
//            return jsonObject;
//
//
//        } catch (IOException e) {
//            jsonObject.put(Consts.CODE,0);
//            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
//        }finally {
//            return jsonObject;
//        }
//
//    }
}
