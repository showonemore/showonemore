package com.jiuye.utils;

import com.jiuye.dao.UserDao;
import com.jiuye.entity.User;

import java.util.ArrayList;
import java.util.List;

public class FindPagesUtils<T> {
    UserDao dao=new UserDao();
   public  PageBean<T> findPages(String currentPage, String pageSize, Class<T> tClass,String search){
        if (currentPage==null || currentPage.trim().equals("")){
            currentPage="1";
        }
        if (pageSize==null || pageSize.trim().equals("")){
            pageSize="3";
        }
       int cpage = Integer.parseInt(currentPage);
       int size = Integer.parseInt(pageSize);
       PageBean<T> pb = new PageBean<>();
       pb.setCurrentPage(cpage);
       pb.setPageSize(size);
       ArrayList<Object> list = new ArrayList<>();
       StringBuffer sb = new StringBuffer("select* form ? where 1=1");
       if (search!=null && !search.trim().equals("")){
           sb.append(" and ? like ?");
           list.add("%"+ search +"%");
       }
       int totalCount = dao.getListEntity(sb.toString(), tClass, list.toArray()).size();
       int totalPage = totalCount%size==0?totalCount/size:totalCount/size+1;
       pb.setTotalCount(totalCount);
       pb.setTotalPage(totalPage);
       sb.append(" limit ?,? ");
       int start = (cpage-1)*size;
       list.add(start);
       list.add(size);
       List<T> list1= (List<T>) dao.getListEntity(sb.toString(),tClass,list.toArray());
       pb.setList(list1);

        return pb;

   }
}
