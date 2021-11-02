package com.jiuye.servlet.after;

import com.jiuye.entity.Course;
import com.jiuye.entity.ResultVo;
import com.jiuye.service.CourseService;
import com.jiuye.utils.*;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/courses")
@MultipartConfig
public class CourseServlet extends BaseServlet {
    CourseService service=new CourseService();
    ResultVo vo;
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用方法获取文件名
        String fileName = UpLoadUtils.upload(request);
        //判断文件是图片还是视频
        if (fileName.endsWith(".jpg")||fileName.endsWith(".png")||fileName.endsWith(".jpeg")||fileName.endsWith(".gif")){
            vo = new ResultVo(200,"上传图片成功",fileName);
        }else {
            vo = new ResultVo(200,"上传视频成功",fileName);
        }
        JsonUtils.objToJson(vo,response);
    }
    //移除文件
    public void fileRemove(HttpServletRequest request,HttpServletResponse response){
        //接收参数
        String fileName = request.getParameter("fileName");
        //截取文件名
        fileName = fileNameUtils.subFileName(fileName);
        //获取存储到服务器的文件地址
        File file = new File("/d:/upload/"+fileName);
        //删除文件
        file.delete();
        //判断删除的文件类型
        if (fileName.endsWith(".jpg")||fileName.endsWith(".png")||fileName.endsWith(".jpeg")||fileName.endsWith(".gif")){
            vo = new ResultVo(200,"删除图片成功",fileName);
        }else {
            vo = new ResultVo(200,"删除视频成功",fileName);
        }
        JsonUtils.objToJson(vo,response);
    }
    //添加课程
    public void insertCourse(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        Course course = new Course();
        //将course对象存储到map中
        BeanUtils.populate(course,map);
        //设置图片的文件名
        course.setCourseImage(fileNameUtils.subFileName(request.getParameter("dialogImageUrl")));
        //设置视频的文件名
        course.setCourseVideo(fileNameUtils.subFileName(request.getParameter("dialogVedioUrl")));
        //调用业务
        int row= service.insertCourse(course);
        if (row>0){
            vo=new ResultVo(200,"添加课程成功",null);
        }else {
            vo=new ResultVo(200,"添加课程失败",null);
        }
        JsonUtils.objToJson(vo,response);

    }
    public void findPages(HttpServletRequest request,HttpServletResponse response){
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
//        String search = request.getParameter("search");
        String courseName = request.getParameter("courseName");
        PageBean<Course> pb= service.findPages(currentPage,pageSize,null,courseName);
        JsonUtils.objToJson(pb,response);
    }
    public void editCourse(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        Course course = new Course();
        BeanUtils.populate(course,map);
        course.setCourseImage(fileNameUtils.subFileName(course.getCourseImage()));
        course.setCourseVideo(fileNameUtils.subFileName(course.getCourseVideo()));
        int row= service.updateCourse(course);
        if (row>0){
            vo=new ResultVo(200,"添加课程成功",null);
        }else {
            vo = new ResultVo(500,"添加课程失败",null);
        }
        JsonUtils.objToJson(vo,response);
    }
    public void delAll(HttpServletRequest request,HttpServletResponse response){
        String cids = request.getParameter("cids");
        int row= service.delAllByCids(cids);
        if (row>0){
            //删除成功
            vo=new ResultVo(200,"批量删除成功",null);
        }else {
            vo=new ResultVo(500,"批量删除失败",null);
        }
        JsonUtils.objToJson(vo,response);
    }
}
