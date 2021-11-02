package com.jiuye.service;

import com.jiuye.dao.CourseDao;
import com.jiuye.dao.CoursedetailDao;
import com.jiuye.entity.Course;
import com.jiuye.entity.Coursedetail;
import com.jiuye.utils.FindPagesUtils;
import com.jiuye.utils.PageBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseService {
    CourseDao dao=new CourseDao();
    CoursedetailDao coursedetailDao= new CoursedetailDao();
    public int insertCourse(Course c) {
        String sql="insert into course values(null,?,?,?,?,?,?,?,?)";
       return dao.update(sql,c.getCourseName(),c.getDescs(),c.getCourseType(),c.getCourseImage(),c.getCourseVideo(),c.getCoursePrice(),c.getStatus(),new Date());

    }
    //分页查找
    public PageBean<Course> findPages(String currentPage, String pageSize,String courseType,String courseName)  {
        if (currentPage==null|| currentPage.trim().equals("")){
            currentPage="1";
        }
        if (pageSize==null|| pageSize.trim().equals("")){
            pageSize="3";
        }
        //将字符串类型转整数类型
        int cpage = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        //创建pagebean对象
        PageBean<Course> pb = new PageBean<>();
        //设置当前页
        pb.setCurrentPage(cpage);
        //设置每页显示条数
        pb.setPageSize(size);
        //创建可变字符串StringBuilder
        StringBuilder sb = new StringBuilder("select * from course where 1=1");
        //新建数组用来存储?内容
        ArrayList<Object> list = new ArrayList<>();
        //通过课程名来搜索
        if (courseName!=null && !courseName.trim().equals("")){
            //字符串追加
            sb.append(" and courseName like ? ");
            list.add("%" + courseName + "%");
        }
        //新增条件，判断课程类型，1为Java类型的课程，2为数据库类型课程，3为HTML类型课程
        //如果不满足以上内容，就不执行该if语句，全部查找
        if (courseType!=null && !courseType.trim().equals("") && !courseType.equals("0")){
            sb.append(" and courseType=? ");
            list.add(courseType);
        }
        //总条数
        int totalCount = dao.getListEntity(sb.toString(), Course.class, list.toArray()).size();
        //总页数
        int totalPage=totalCount%size==0?totalCount/size:totalCount/size+1;
        pb.setTotalPage(totalPage);
        pb.setTotalCount(totalCount);
        sb.append(" limit ?,? ");
        int start = (cpage-1)*size;
        list.add(start);
        list.add(size);
        List<Course> courseList= dao.getListEntity(sb.toString(),Course.class,list.toArray());
        pb.setList(courseList);
        return  pb;
    }
    //更新课程
    public int updateCourse(Course c) {
        String sql="update course set courseName=?, descs=?,courseType=?,courseImage=?,courseVideo=?,coursePrice=?,status=?,createTime=? where cid=?";

        return dao.update(sql,c.getCourseName(),c.getDescs(),c.getCourseType(),c.getCourseImage(),c.getCourseVideo(),c.getCoursePrice(),c.getStatus(),new Date(),c.getCid());
    }
    //根据id批量删除
    public int delAllByCids(String cids) {
        String sql="delete from course where cid in("+ cids +")";
        return dao.update(sql);
    }
    //查找所有的课程
    public List<Course> findAllCourse() {
        String sql="select * from course";
        return dao.getListEntity(sql, Course.class);
    }
    //查找课程类型
    public List<Course> findCourseType(String courseType) {
        int size=0;
        //判断课程类型，获取需要显示的条数
        if ("1".equals(courseType)){
            size=8;
        }else if ("2".equals(courseType)){
            size=9;
        }else if ("3".equals(courseType)){
            size=6;
        }
        String sql = "select * from course where courseType=? limit 0,?";
        return dao.getListEntity(sql, Course.class, courseType, size);
    }

    public Course findCourse(String cid) {
        String sql="select * from course where cid=?";
        Course course = dao.getEntity(sql, Course.class, cid);
        sql="select * from coursedetail where cid=?";
        List<Coursedetail> coursedetails = coursedetailDao.getListEntity(sql, Coursedetail.class, cid);
        course.setList(coursedetails);
        return course;
    }
}
