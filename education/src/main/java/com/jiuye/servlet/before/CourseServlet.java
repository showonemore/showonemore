package com.jiuye.servlet.before;

import com.jiuye.entity.Course;
import com.jiuye.service.CourseService;
import com.jiuye.utils.BaseServlet;
import com.jiuye.utils.JsonUtils;
import com.jiuye.utils.PageBean;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/b_course")
public class CourseServlet extends BaseServlet {
    CourseService service= new CourseService();
    public void findCourseType(HttpServletRequest request, HttpServletResponse response){
        String courseType = request.getParameter("courseType");
        List<Course> list= service.findCourseType(courseType);
        JsonUtils.objToJson(list,response);
    }
    public void findPages(HttpServletRequest request, HttpServletResponse response){
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        String courseType = request.getParameter("courseType");
        String courseName = request.getParameter("courseName");
        PageBean<Course> pb = service.findPages(currentPage, pageSize, courseType, courseName);
        JsonUtils.objToJson(pb,response);
    }
    public void findCourse(HttpServletRequest request, HttpServletResponse response){
        String cid = request.getParameter("cid");
        Course c= service.findCourse(cid);
        JsonUtils.objToJson(c,response);
    }
}
