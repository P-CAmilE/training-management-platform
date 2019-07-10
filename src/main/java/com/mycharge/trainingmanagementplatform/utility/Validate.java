package com.mycharge.trainingmanagementplatform.utility;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Validate {

    //验证token
    static public boolean valiToken(HttpServletRequest request){
        //从cookie中获取token
        Cookie[] cookies = request.getCookies();
        String token1 = new String();
        for (Cookie cookie : cookies) {
            switch (cookie.getName()) {
                case "token":
                    token1 = cookie.getValue();
                    break;
                default:
                    break;
            }
        }
        //服务器中的token
        Object token2 =  request.getSession().getAttribute("token");
        if(token1!=null &&token2!=null &&token1.equals(token2)){
            //request.getSession().removeAttribute("token");
            return true;
        }
        return false;
    }


    //验证角色权限
    static public boolean valiRole(HttpServletRequest request,int role_group ){
        int r = (int)request.getSession().getAttribute("role");
        if((role_group&r)==0){
            return false;
        }
        return true;
    }

}
