package com.jiuye.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String method = req.getParameter("method");
        //获取字节码文件
        Class<? extends BaseServlet> aClass = this.getClass();
        try {
            //通过字节码文件获取方法
            Method m = aClass.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            m.invoke(aClass.newInstance(),req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
