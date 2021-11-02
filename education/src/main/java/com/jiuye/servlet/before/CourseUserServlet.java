package com.jiuye.servlet.before;

import com.jiuye.entity.Course;
import com.jiuye.entity.CourseUser;
import com.jiuye.service.CourseUserService;
import com.jiuye.utils.BaseServlet;
import com.jiuye.utils.JsonUtils;

import javax.security.auth.login.CredentialException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/b_c_u")
public class CourseUserServlet extends BaseServlet {
    CourseUserService service= new CourseUserService();
    public void insertCourseUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cid = request.getParameter("cid");
        String uid = request.getParameter("uid");
        int row= service.insertCourseUser(cid,uid);
        if (row>0){
            response.sendRedirect("http://192.168.10.88:5501/%E5%B0%8Fu%E5%AD%A6%E4%B9%A0/pages/course.html");
        }else {
            throw new RuntimeException("关联课程失败");
        }
    }
    public void findCourse(HttpServletRequest request, HttpServletResponse response){
        String uid = request.getParameter("uid");
        List<Course> list = service.findCourse(uid);
        JsonUtils.objToJson(list,response);
    }
    public void findCourseUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cid = request.getParameter("cid");
        String uid = request.getParameter("uid");
        CourseUser user = service.findCourseUser(cid,uid);
        if (user!=null){
            response.getWriter().print(1);
        }else {
            response.getWriter().print(0);
        }
    }
}
