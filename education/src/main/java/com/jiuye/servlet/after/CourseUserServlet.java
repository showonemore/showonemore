package com.jiuye.servlet.after;

import com.jiuye.entity.Course;
import com.jiuye.entity.CourseUser;
import com.jiuye.entity.ResultVo;
import com.jiuye.service.CourseService;
import com.jiuye.service.CourseUserService;
import com.jiuye.utils.BaseServlet;
import com.jiuye.utils.JsonUtils;
import com.jiuye.utils.PageBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/c_u")
public class CourseUserServlet extends BaseServlet {
    ResultVo vo;
    CourseService courseService= new CourseService();
    CourseUserService service= new CourseUserService();
    //分页查找
    public void findPages(HttpServletRequest request, HttpServletResponse response){
        //获取参数
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        //调用业务
        PageBean<CourseUser> pb= service.findPages(currentPage,pageSize);
        JsonUtils.objToJson(pb,response);
    }
    //查找所有的课程
    public void findAllCourse(HttpServletRequest request, HttpServletResponse response){
        //调用业务，返回课程对象存储到List集合中
        List<Course> list= courseService.findAllCourse();
        JsonUtils.objToJson(list,response);
    }
    //更新用户课程
    public void updateCourseUser(HttpServletRequest request, HttpServletResponse response){
        //获取参数
        String id = request.getParameter("id");
        String cid = request.getParameter("cid");
        //调用业务
        int row= service.updateCourseUser(id,cid);
        if (row>0){
            vo = new ResultVo(200,"选课修改成功",null);
        }else {
            vo = new ResultVo(500,"选课修改失败",null);
        }
        JsonUtils.objToJson(vo,response);
    }
    public void delAll(HttpServletRequest request, HttpServletResponse response){
        String ids = request.getParameter("ids");
        int row= service.delAllById(ids);
        if (row>0){
            vo = new ResultVo(200,"批量删除成功",null);
        }else {
            vo = new ResultVo(500,"批量删除失败",null);
        }
        JsonUtils.objToJson(vo,response);
    }
}
