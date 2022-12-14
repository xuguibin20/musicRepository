package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.domain.Singer;
import com.javaclimb.music.service.SingerService;

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
import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;



@RestController
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;

    /**
     *添加歌手
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        //获取前端传过来的参数
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String pic = "img/singerPic/mn.jpg";//默认图片，这里在后端写死先；也可以后端接收不写，在前端写死  params.append("pic", "img/singerPic/**.jpg");
//        String pic = request.getParameter("pic").trim();////歌手头像，这里在前端写死先，在后端接收；也可以前端不写，在后端写死String pic = "img/singerPic/**.jpg";
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//保存到歌手的对象中
        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(new java.sql.Date(birthDate.getTime()));
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean flag = singerService.insert(singer);
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
     * 修改歌手
     */
    @RequestMapping(value ="/update",method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();

        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到歌手的对象中
        Singer singer = new Singer();
        singer.setName(name);
        singer.setId(Integer.parseInt(id));
        singer.setSex(new Byte(sex));
        singer.setBirth(new java.sql.Date (birthDate.getTime()));
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean flag = singerService.update(singer);
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
     * 删除歌手
     */
    @RequestMapping(value ="/delete",method = RequestMethod.GET)
    public Object deleteSinger(HttpServletRequest request){
        //-TOOD 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
        String id = request.getParameter("id").trim();//主键
        //根据歌曲的id，找到对应的pic地址
        String Path = singerService.selectByPrimaryKey(Integer.parseInt(id)).getPic();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") ;
        //文件在后端文件夹的地址
        File dest = new File(filePath  +   Path);
        dest.delete();
        boolean flag = singerService.delete(Integer.parseInt(id));
        return flag;

    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value ="/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();//主键
        return singerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     *查询所有歌手
     */
    @RequestMapping(value ="/allSinger",method = RequestMethod.GET)
        public Object allSinger(HttpServletRequest request){
        return singerService.allSinger();
    }

    /**
     * 根据歌手名字模糊查询列表
     */
    @RequestMapping(value ="/singerofName",method = RequestMethod.GET)
    public Object singerOfName(HttpServletRequest request){
        String name = request.getParameter("name").trim();//歌手名字
        return singerService.singerOfName("%"+name+"%");
    }

    /**
     * 根据性别查询
     */
    @RequestMapping(value ="/singerofSex",method = RequestMethod.GET)
    public Object singerOfSex(HttpServletRequest request){
        String sex = request.getParameter("sex").trim();//歌手性别
        return singerService.singerOfSex(Integer.parseInt(sex));
    }

    /**
     *更新歌手图片
     */
    @RequestMapping(value ="/updateSingerPic",method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile,@RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
      if(avatorFile.isEmpty()){
          jsonObject.put(Consts.CODE,0);
          jsonObject.put(Consts.MSG,"文件上传失败");
          return jsonObject;
      }
        //-TOOD 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
        //根据歌曲的id，找到对应的pic地址
        String Path = singerService.selectByPrimaryKey(id).getPic();
        String pic = "img/singerPic/mn.jpg";
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
      String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+System.getProperty("file.separator")+"singerPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "img/singerPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            //没有存储权限或磁盘满了，如果更新异常，不该更新数据库
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            Boolean flag = singerService.update(singer);
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
