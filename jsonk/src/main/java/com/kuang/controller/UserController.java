package com.kuang.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kuang.pojo.User;
import com.kuang.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping("/json1")
    @ResponseBody
    public String json1() throws JsonProcessingException {
        //创建一个jackson的对象映射器，用来解析数据
        ObjectMapper mapper = new ObjectMapper();
        //创建一个对象
        User user = new User("秦疆1号", 3, "男");
        //将我们的对象解析成为json格式
        String str = mapper.writeValueAsString(user);
        //由于@ResponseBody注解，这里会将str转成json格式返回；十分方便
        return str;
    }
    @RequestMapping(value ="/json2")
    @ResponseBody
    public String json2() throws JsonProcessingException {

        List<User> list = new ArrayList<>();

        User user1 = new User("秦疆1号", 3, "男");
        User user2 = new User("秦疆2号", 3, "男");
        User user3 = new User("秦疆3号", 3, "男");
        User user4 = new User("秦疆4号", 3, "男");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);

        return new ObjectMapper().writeValueAsString(list);
    }
    @RequestMapping(value ="/time1")
    @ResponseBody
    public String json3() throws JsonProcessingException {

        Date date = new Date();
        //发现问题，返回时间为时间戳
        System.out.println(date);

        return new ObjectMapper().writeValueAsString(date);
    }
    @RequestMapping(value ="/time2")
    @ResponseBody
    public String json4() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        //关闭时间戳功能
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //时间格式化，自定义日期格式对象
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //让mapper指定时间日期格式为simpleDateFormat
        mapper.setDateFormat(simpleDateFormat);
        //写一个日期对象
        Date date = new Date();

        return mapper.writeValueAsString(date);
    }
    //给他一个工具类
    @RequestMapping(value ="/time3")
    @ResponseBody
    public String json5() throws JsonProcessingException {

        return JsonUtils.getJson(new Date());
    }

}