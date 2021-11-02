package com.jiuye.entity;


import java.util.List;

public class CourseUser {

  private int id;
  private int cid;
  private int uid;
  private Course course;
  private User user;

  public CourseUser(int id, int cid, int uid, Course course, User user) {
    this.id = id;
    this.cid = cid;
    this.uid = uid;
    this.course = course;
    this.user = user;
  }

  public CourseUser() {
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getCid() {
    return cid;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }


  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

}
