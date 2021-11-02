package com.jiuye.servlet.before;

import com.jiuye.entity.Coursedetail;
import com.jiuye.service.CourseDetailService;
import com.jiuye.utils.BaseServlet;
import com.jiuye.utils.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
@WebServlet("/b_detail")
public class CourseDetailServlet extends BaseServlet {
    CourseDetailService service= new CourseDetailService();
    public void findCourseDetail(HttpServletRequest request, HttpServletResponse response){
        String cid = request.getParameter("cid");
        Map<String, List<Coursedetail>> map = service.findCourseDetail(cid);
        JsonUtils.objToJson(map,response);
    }
}
