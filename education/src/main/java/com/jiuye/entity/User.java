package com.jiuye.entity;


public class User {

  private int uid;//编号
  private String name;//真实姓名
  private String phone;//手机号
  private int age;
  private int sex;
  private String username;//用户名
  private String password;
  private int status;//用户状态，1：启用 2：禁用
  private String createtime; //注册时间
  private int role; //用户角色 1：管理员 2：经理 3：员工
  private String picture;//用户头像路径

  public User() {
  }

  public User(int uid, String name, String phone, int age, int sex, String username, String password, int status, String createtime, int role, String picture) {
    this.uid = uid;
    this.name = name;
    this.phone = phone;
    this.age = age;
    this.sex = sex;
    this.username = username;
    this.password = password;
    this.status = status;
    this.createtime = createtime;
    this.role = role;
    this.picture = picture;
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }


  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }


  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }


  public int getRole() {
    return role;
  }

  public void setRole(int role) {
    this.role = role;
  }


  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

}
