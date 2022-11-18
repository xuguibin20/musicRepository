package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.domain.Consumer;
import com.javaclimb.music.service.ConsumerService;
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


/**
 * 前端用户Controller
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    /**
     * 用于后台管理的添加用户
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addConsumer(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        //获取前端传过来的参数
        String username = request.getParameter("username").trim();//账号
        String password = request.getParameter("password").trim();//密码
        String sex = request.getParameter("sex").trim();//性别
        String location = request.getParameter("location").trim();//地区
        String introduction = request.getParameter("introduction").trim();//简介
        String phoneNum = request.getParameter("phoneNum").trim();//手机号码
        String email = request.getParameter("email").trim();//邮箱
        String birth = request.getParameter("birth").trim();//生日
        String avator = "img/consumerAvator/mn.jpg";//默认图片，这里在后端写死先；也可以后端接收不写，在前端写死  params.append("avator", "img/consumerAvator/**.jpg");
        //String avator = request.getParameter("avator").trim();////用户头像，这里在前端写死先，在后端接收；也可以前端不写，在后端写死String avator = "img/consumerAvator/**.jpg";
        if (username == null || "".equals(username)) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名不能为空");
            return jsonObject;
        }
        if (password == null || "".equals(password)) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "密码不能为空");
            return jsonObject;
        }
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到用户的对象中
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setAvator(avator);
        consumer.setSex(new Byte(sex));
        consumer.setEmail(email);
        consumer.setPhoneNum(phoneNum);
        consumer.setBirth(new java.sql.Date(birthDate.getTime()));
        consumer.setLocation(location);
        consumer.setIntroduction(introduction);
        boolean flag = consumerService.insert(consumer);
        if (flag) {  //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "添加失败");
            return jsonObject;
        }

    }


    /**
     * 修改用户
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateConsumer(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();//主键
        String username = request.getParameter("username").trim();//账号
        String password = request.getParameter("password").trim();//密码
        String sex = request.getParameter("sex").trim();//性别
        String birth = request.getParameter("birth").trim();//生日
        String location = request.getParameter("location").trim();//地区
        String introduction = request.getParameter("introduction").trim();//简介
        String phoneNum = request.getParameter("phoneNum").trim();//手机号码
        String email = request.getParameter("email").trim();//邮箱
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();

        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到用户的对象中
        Consumer consumer = new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setEmail(email);
        consumer.setPhoneNum(phoneNum);
        consumer.setBirth(new java.sql.Date(birthDate.getTime()));
        consumer.setLocation(location);
        consumer.setIntroduction(introduction);
        boolean flag = consumerService.update(consumer);
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
     * 删除用户
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteConsumer(HttpServletRequest request) {
        //-TOOD 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
        String id = request.getParameter("id").trim();//主键
        //根据歌曲的id，找到对应的avator地址
        String Path = consumerService.selectByPrimaryKey(Integer.parseInt(id)).getAvator();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator");
        //文件在后端文件夹的地址
        File dest = new File(filePath + Path);
        dest.delete();
        boolean flag = consumerService.delete(Integer.parseInt(id));
        return flag;

    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();//主键
        return consumerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询所有用户
     */
    @RequestMapping(value = "/allConsumer", method = RequestMethod.GET)
    public Object allConsumer(HttpServletRequest request) {
        return consumerService.allConsumer();
    }

    /**
     * 根据用户名字模糊查询列表
     */
    @RequestMapping(value = "/consumerOflikeName", method = RequestMethod.GET)
    public Object consumerOflikeName(HttpServletRequest request) {
        String username = request.getParameter("username").trim();//用户名字
        return consumerService.consumerOflikeName("%" + username + "%");
    }

    /**
     * 更新用户图片
     */
    @RequestMapping(value = "/updateConsumerAvator", method = RequestMethod.POST)
    public Object updateConsumerAvator(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }
        //-TOOD 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
        //根据用户的id，找到对应的avator地址
        String Path = consumerService.selectByPrimaryKey(id).getAvator();
        String avator = "img/consumerAvator/mn.jpg";
        if (Path.equals(avator)) {
            int a = -1;//什么都不做
        } else {
            //文件路径
            String filePath1 = System.getProperty("user.dir") + System.getProperty("file.separator");
            //文件在后端文件夹的地址
            File dest1 = new File(filePath1 + Path);
            dest1.delete();
        }
        //文件名=当前时间（毫秒）+原来的文件名
        String fileUsername = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "consumerAvator";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileUsername);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "img/consumerAvator/" + fileUsername;
        try {
            avatorFile.transferTo(dest);
            //没有存储权限或磁盘满了，如果更新异常，不该更新数据库
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatorPath);
            Boolean flag = consumerService.update(consumer);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("avator", storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败");
            return jsonObject;


        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败" + e.getMessage());
        } finally {
            return jsonObject;
        }

    }

    /**
     * 注册用户
     */
    @RequestMapping(value = "/registerConsumer", method = RequestMethod.POST)
    public Object registerConsumer(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        //获取前端传过来的参数
        String username = request.getParameter("username").trim();//账号
        String password = request.getParameter("password").trim();//密码
        String avator = "img/consumerAvator/mn.jpg";//默认图片，这里在后端写死先；也可以后端接收不写，在前端写死  params.append("avator", "img/consumerAvator/**.jpg");
        //String avator = request.getParameter("avator").trim();////用户头像，这里在前端写死先，在后端接收；也可以前端不写，在后端写死String avator = "img/consumerAvator/**.jpg";
        if(username ==null || "".equals(username)){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password ==null || "".equals(password)){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }
        //检查要注册的用户名是否存在
        Consumer consumer1 = consumerService.selectByUsername(username);
//        boolean flag = consumerService.verifyUsername(username);鸡肋，可以删了
        if(consumer1 != null ){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"注册失败，用户名已存在");
            return jsonObject;

        }else {
            //不存在，保存到用户的对象中
            Consumer consumer = new Consumer();
            consumer.setUsername(username);
            consumer.setPassword(password);
            consumer.setAvator(avator);
            boolean flag1 = consumerService.insert(consumer);
            if(flag1){
                //保存成功
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"注册成功");
                return jsonObject;
            }else{
                jsonObject.put(Consts.CODE,0);
                jsonObject.put(Consts.MSG,"注册失败");
                return jsonObject;
            }
        }
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/loginConsumer", method = RequestMethod.POST)
    public Object loginConsumer(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        //获取前端传过来的参数
        String username = request.getParameter("username").trim();//账号
        String password = request.getParameter("password").trim();//密码
        if(username ==null || "".equals(username)){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password ==null || "".equals(password)){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }
        //验证用户密码是否正确
        boolean flag = consumerService.vertifyUser(username,password);
        if(flag ){

            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登录成功");
//            登陆成功，返回登录的对象
//           return  consumerService.selectByUsername(username);
            jsonObject.put("userMsg",consumerService.selectByUsername(username));
            return jsonObject;

            }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户或密码错误");
            return jsonObject;
            }
        }
}
