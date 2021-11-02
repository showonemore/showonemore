package com.jiuye.fiter;

import com.jiuye.entity.ResultVo;
import com.jiuye.entity.User;
import com.jiuye.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class CorsFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        String method = request.getParameter("method");
        String referer = request.getHeader("Referer");
        if ("insertCourseUser".equals(method) ||referer.contains("5501")||requestURI.contains("/code")||"login".equals(method)||"loginOut".equals(method)){
            filterChain.doFilter(request,response);
        }else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user!=null){
                filterChain.doFilter(request,response);
            }else {
                ResultVo vo=new ResultVo(401,"请登录后再操作",null);
                JsonUtils.objToJson(vo,response);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
