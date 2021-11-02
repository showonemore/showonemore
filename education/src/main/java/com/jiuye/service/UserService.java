package com.jiuye.service;

import com.jiuye.dao.UserDao;
import com.jiuye.entity.Page;
import com.jiuye.entity.User;
import com.jiuye.utils.PageBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService {
    UserDao dao=new UserDao();
    //登录业务
    public User login(String username, String password) {
        String sql ="select * from user where username = ? and password = ?";
        return dao.getEntity(sql,User.class,username,password);
    }
    //查找页业务
    public PageBean<User> findPages(String currentPage, String pageSize, String search) {
        if (currentPage==null ||currentPage.trim().equals("")){
            currentPage="1";
        }
        if (pageSize==null || pageSize.trim().equals("")){
            pageSize="3";
        }
        int cpage = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        PageBean<User> pb = new PageBean<>();
        pb.setCurrentPage(cpage);
        pb.setPageSize(size);
        StringBuilder sb = new StringBuilder("select * from user where 1=1 ");
        ArrayList<Object> list = new ArrayList<>();
        if (search!=null && !search.trim().equals("")){
            sb.append(" and name like ? ");
            list.add("%" + search + "%");
        }
        int totalCount = dao.getListEntity(sb.toString(), User.class, list.toArray()).size();
        pb.setTotalCount(totalCount);
        int totalPage = totalCount%size==0?totalCount/size:totalCount/size+1;
        pb.setTotalPage(totalPage);
        sb.append(" limit ?,?");
        int start = (cpage-1)*size;
        list.add(start);
        list.add(size);
        List<User> userList= dao.getListEntity(sb.toString(),User.class,list.toArray());
        pb.setList(userList);
        return pb;
    }

    public int updateUser(User u) {
        String sql = "update user set name=?,phone=?,age=?,sex=?,username=?,password=?,status=?,createtime=?,role=? where uid=?";
        return dao.update(sql,u.getName(),u.getPhone(),u.getAge(),u.getSex(),u.getUsername(),u.getPassword(),u.getStatus(),u.getCreatetime(),u.getRole(),u.getUid());
    }

    public int addUser(User u) {
        String sql ="insert into user values(null,?,?,?,?,?,?,?,?,?,?)";
        return dao.update(sql,u.getName(),u.getPhone(),u.getAge(),u.getSex(),u.getUsername(),u.getPassword(),u.getStatus(),new Date(),u.getRole(),u.getPicture());
    }

    public int delUserByUids(String uids) {
        String sql="delete from user where uid in("+ uids+")";
        return dao.update(sql);
    }

    public User checkTel(String telephone) {
        return dao.getEntity("select * from user where phone=?",User.class,telephone);
    }

    public User checkLogin(String telephone, String password) {
        String sql="select * from user where phone=? and password=?";
        return dao.getEntity(sql,User.class,telephone,password);
    }
}
