package com.jiuye.servlet.after;

import com.jiuye.entity.ResultVo;
import com.jiuye.entity.User;
import com.jiuye.service.UserService;
import com.jiuye.utils.BaseServlet;
import com.jiuye.utils.JsonUtils;
import com.jiuye.utils.PageBean;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends BaseServlet {
    ResultVo vo;
    UserService service =new UserService();
    //登录
    public void login(HttpServletRequest request, HttpServletResponse response){
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        //创建session对象
        HttpSession session = request.getSession();
        //将验证码保存到session对象中
        String verifyCode = (String) session.getAttribute("verifyCode");
        //先判断验证码是否相同
        if (verifyCode.equalsIgnoreCase(code)){
            //相同则进一步判断user是否为空
            User user = service.login(username, password);
            if (user!=null){
                //user不为空则进一步判断账号是否是被禁用
                //1为可以使用，2为禁用
                if (user.getStatus()!=1){
                    //账号被禁用
                    vo= new ResultVo(500,"您的账号已被封禁",null);
                }else {
                    //可以正常使用，再判断用户角色，若role为1是管理员、2为经理、3为员工
                    if (user.getRole()!=1){
                        //用户无权限
                        vo=new ResultVo(500,"用户权限不足",null);
                    }else {
                        //用户是管理员，登录成功
                        session.setAttribute("user",user);
                        vo=new ResultVo(200,"登录成功",null);
                    }
                }
            }else {
                vo=new ResultVo(500,"用户名或密码错误",null);
            }
        }else {
            vo = new ResultVo(500, "验证码错,请重新输入", null);
        }
        JsonUtils.objToJson(vo,response);
    }
    //用户查找
    public void findUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(); //创建session对象
        User user = (User) session.getAttribute("user"); //从session中获取user对象
        if (user!=null){  //判断获取的user对象是否为空
            vo=new ResultVo(200,"获取用户信息成功",user);
        }else {
            vo=new ResultVo(500,"获取用户信息失败",null);
        }
        JsonUtils.objToJson(vo,response);//将带有user对象的vo对象返回给页面
    }
    //退出登录
    public void loginOut(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(); //获取session对象
        session.invalidate(); //销毁session对象
        vo = new ResultVo(200,"退出成功",null);
        JsonUtils.objToJson(vo,response);
    }
    public void findPages(HttpServletRequest request, HttpServletResponse response){
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        String search = request.getParameter("search");
        PageBean<User> pb = service.findPages(currentPage,pageSize,search);
        JsonUtils.objToJson(pb,response);
    }
    public void upDateUser(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        BeanUtils.populate(user,map);
        int row= service.updateUser(user);
        if (row>0){
            vo = new ResultVo(200,"用户信息修改成功",null);
        }else {
            vo = new ResultVo(500,"用户信息修改失败",null);
        }
        JsonUtils.objToJson(vo,response);
    }
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        BeanUtils.populate(user,map);
        int row= service.addUser(user);
        if (row>0){
            vo = new ResultVo(200,"添加用户成功",null);
        }else {
            vo = new ResultVo(500,"添加用户失败",null);
        }
        JsonUtils.objToJson(vo,response);
    }
    public void delUser(HttpServletRequest request, HttpServletResponse response){
        String uids = request.getParameter("uids");
        int row= service.delUserByUids(uids);
        if (row>0){
            vo= new ResultVo(200,"批量删除成功",null);
        }else {
            vo= new ResultVo(500,"批量删除失败",null);

        }
        JsonUtils.objToJson(vo,response);
    }
}
