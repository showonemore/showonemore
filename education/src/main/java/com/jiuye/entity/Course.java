package com.jiuye.entity;


import java.util.List;

public class Course {

  private int cid;
  private String courseName;
  private String descs;
  private int courseType;
  private String courseImage;
  private String courseVideo;
  private double coursePrice;
  private int status;
  private String createTime;
  private List<Coursedetail> list;

  public Course(int cid, String courseName, String descs, int courseType, String courseImage, String courseVideo, double coursePrice, int status, String createTime, List<Coursedetail> list) {
    this.cid = cid;
    this.courseName = courseName;
    this.descs = descs;
    this.courseType = courseType;
    this.courseImage = courseImage;
    this.courseVideo = courseVideo;
    this.coursePrice = coursePrice;
    this.status = status;
    this.createTime = createTime;
    this.list = list;
  }

  public Course() {
  }

  public int getCid() {
    return cid;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public String getDescs() {
    return descs;
  }

  public void setDescs(String descs) {
    this.descs = descs;
  }

  public int getCourseType() {
    return courseType;
  }

  public void setCourseType(int courseType) {
    this.courseType = courseType;
  }

  public String getCourseImage() {
    return courseImage;
  }

  public void setCourseImage(String courseImage) {
    this.courseImage = courseImage;
  }

  public String getCourseVideo() {
    return courseVideo;
  }

  public void setCourseVideo(String courseVideo) {
    this.courseVideo = courseVideo;
  }

  public double getCoursePrice() {
    return coursePrice;
  }

  public void setCoursePrice(double coursePrice) {
    this.coursePrice = coursePrice;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public List<Coursedetail> getList() {
    return list;
  }

  public void setList(List<Coursedetail> list) {
    this.list = list;
  }
}
