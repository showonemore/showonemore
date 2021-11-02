package com.jiuye.servlet.before;

import com.jiuye.entity.ResultVo;
import com.jiuye.entity.User;
import com.jiuye.service.UserService;
import com.jiuye.utils.BaseServlet;
import com.jiuye.utils.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/b_user")
public class UserServlet extends BaseServlet {
    ResultVo vo;
    UserService service= new UserService();
    public void checkTel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String telephone = request.getParameter("telephone");
        User user= service.checkTel(telephone);
        if (user==null){
            //手机号可以使用
            response.getWriter().print(1);
        }else {
            //手机号不可用
            response.getWriter().print(0);
        }
    }
    public void register(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String telephone = request.getParameter("telephone");
        String password = request.getParameter("password");
        User user = new User();
        user.setName(name);
        user.setPhone(telephone);
        user.setPassword(password);
        user.setStatus(1);
        user.setRole(3);
        int row= service.addUser(user);
        if (row>0){
            vo = new ResultVo(200,"注册成功",null);
        }else {
            vo = new ResultVo(500,"注册失败",null);
        }
        JsonUtils.objToJson(vo,response);
    }
    public void checkLogin(HttpServletRequest request, HttpServletResponse response){
        String telephone = request.getParameter("telephone");
        String password = request.getParameter("password");
        User user= service.checkLogin(telephone,password);
        if (user!=null){
            if (user.getStatus()==2){
                vo = new ResultVo(500,"账号已被封禁",null);
            }else {
                vo = new ResultVo(200,"登录成功",user);
            }
        }
        JsonUtils.objToJson(vo,response);
    }

}
