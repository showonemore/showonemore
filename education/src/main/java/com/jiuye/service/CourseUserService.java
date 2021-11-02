package com.jiuye.service;

import com.jiuye.dao.CourseDao;
import com.jiuye.dao.CourseUserDao;
import com.jiuye.dao.UserDao;
import com.jiuye.entity.Course;
import com.jiuye.entity.CourseUser;
import com.jiuye.entity.User;
import com.jiuye.utils.PageBean;

import java.util.ArrayList;
import java.util.List;

public class CourseUserService {
    CourseUserDao courseUserDao= new CourseUserDao();
    CourseDao courseDao= new CourseDao();
    UserDao userDao= new UserDao();
    public PageBean<CourseUser> findPages(String currentPage, String pageSize) {
        if (currentPage==null || currentPage.trim().equals("")){
            currentPage="1";
        }
        if (pageSize==null || pageSize.trim().equals("")){
            pageSize="3";
        }
        int cpage = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        PageBean<CourseUser> pb = new PageBean<>();
        pb.setCurrentPage(cpage);
        pb.setPageSize(size);
        //计算总条数
        int totalCount = courseUserDao.getListEntity("select * from course_user", CourseUser.class).size();
        int totalPage=totalCount%size==0?totalCount/size:totalCount/size+1;
        pb.setTotalCount(totalCount);
        pb.setTotalPage(totalPage);
        List<CourseUser> courseUserList = courseUserDao.getListEntity("select * from course_user limit ?,?", CourseUser.class, (cpage - 1) * size, size);
        for (CourseUser courseUser : courseUserList){
            Course course = courseDao.getEntity("select * from course where cid=?", Course.class, courseUser.getCid());
            User user = userDao.getEntity("select* from user where uid=?", User.class, courseUser.getUid());
            courseUser.setCourse(course);
            courseUser.setUser(user);
        }
        pb.setList(courseUserList);
        return pb;
    }

    public int updateCourseUser(String id, String cid) {
        String sql = "update course_user set cid=? where id=?";
        return courseUserDao.update(sql,cid,id);
    }

    public int delAllById(String ids) {
        String sql="delete from course_user where id in("+ ids +")";
        return courseUserDao.update(sql);
    }

    public int insertCourseUser(String cid, String uid) {
        String sql="insert into course_user values(null,?,?)";
        return courseUserDao.update(sql,cid,uid);
    }

    public List<Course> findCourse(String uid) {
        ArrayList<Course> courseList = new ArrayList<>();
        String sql= "select * from course_user where uid=?";
        List<CourseUser> courseuserList = courseUserDao.getListEntity(sql, CourseUser.class, uid);
        sql="select * from course where cid=?";
        for (CourseUser courseUser:courseuserList){
            int cid = courseUser.getCid();
            Course course = courseDao.getEntity(sql, Course.class, cid);
            courseList.add(course);
        }
        return courseList;
    }

    public CourseUser findCourseUser(String cid, String uid) {
        String sql="select * from course_user where cid=? and uid=?";
        return courseUserDao.getEntity(sql,CourseUser.class,cid,uid);
    }
}
