package com.jiuye.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletResponse;
//将数据回显到页面
public class JsonUtils {
    public static void objToJson(Object obj, HttpServletResponse response){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(obj);
            response.getWriter().print(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
