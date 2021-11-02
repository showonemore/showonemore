package com.jiuye.service;

import com.jiuye.dao.CoursedetailDao;
import com.jiuye.entity.Coursedetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDetailService {
    CoursedetailDao dao= new CoursedetailDao();
    public int addDetail(Coursedetail cd) {
        String sql="insert into coursedetail values(null,?,?,?,?,?)";
        if ("1".equals(cd.getType())){
            cd.setType("章节一");
        }else if ("2".equals(cd.getType())){
            cd.setType("章节二");
        }else if ("3".equals(cd.getType())){
            cd.setType("章节三");
        }
        return dao.update(sql,cd.getName(),cd.getType(),cd.getUrl(),cd.getStart_data(),cd.getCid());
    }

    public Map<String, List<Coursedetail>> findCourseDetail(String cid) {
        HashMap<String, List<Coursedetail>> map = new HashMap<>();
        String sql="select * from coursedetail where cid=? group by type";
        List<Coursedetail> coursedetails = dao.getListEntity(sql, Coursedetail.class, cid);
        for (Coursedetail detail : coursedetails){
            String type = detail.getType();
            sql="select * from coursedetail where cid= ? and type=?";
            List<Coursedetail> coursedetailList = dao.getListEntity(sql, Coursedetail.class, cid, type);
            map.put(type,coursedetailList);
        }
        return map;
    }
}
