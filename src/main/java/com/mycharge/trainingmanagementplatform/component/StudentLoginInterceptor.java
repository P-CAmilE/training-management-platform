package com.mycharge.trainingmanagementplatform.component;

import com.mycharge.trainingmanagementplatform.global.Role;
import com.mycharge.trainingmanagementplatform.utility.Validate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StudentLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(true);
        Cookie cookies[] = request.getCookies();
        if (null == cookies) {
            System.out.println("没有cookie====");
        } else {
//      遍历cookie如果找到登录状态则返回true执行原来controller的方法
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("acc_id")) {
                    if(Validate.valiRole(request, Role.role_group.get("student"))) {
                        return true;
                    }
                }
            }
        }
//     没有找到登录状态则重定向到登录页，返回false，不执行原来controller的方法
        response.sendRedirect("/IndexHome");
        return false;
    }
}
