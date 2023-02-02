package com.kuang.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class JsonUtils {
    public  static  String getJson(Object object){
        return getJson(object,"yyyy-MM-dd HH:mm:ss");
    }

    public  static  String getJson(Object object,String dateFormat){
        ObjectMapper mapper = new ObjectMapper();
        //关闭时间戳功能
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //时间格式化，自定义日期格式对象
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        //让mapper指定时间日期格式为simpleDateFormat
        mapper.setDateFormat(simpleDateFormat);
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }
}
