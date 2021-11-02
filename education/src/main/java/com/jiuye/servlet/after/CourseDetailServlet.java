package com.jiuye.servlet.after;

import com.jiuye.entity.Coursedetail;
import com.jiuye.entity.ResultVo;
import com.jiuye.service.CourseDetailService;
import com.jiuye.utils.BaseServlet;
import com.jiuye.utils.JsonUtils;
import com.jiuye.utils.fileNameUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/detail")
public class CourseDetailServlet extends BaseServlet {
    ResultVo vo;
    CourseDetailService service=new CourseDetailService();
    public void addDetail(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
        Coursedetail coursedetail = new Coursedetail();
        BeanUtils.populate(coursedetail,map);
        coursedetail.setUrl(fileNameUtils.subFileName(coursedetail.getUrl()));
        int row= service.addDetail(coursedetail);
        if (row > 0){
            vo = new ResultVo(200,"添加课程明细成功",null);
        }else {
            vo = new ResultVo(500,"添加课程明细失败",null);
        }
        JsonUtils.objToJson(vo,response);
    }
}
