package com.thesilentnights.maalinkserver.controller;

import com.thesilentnights.maalinkserver.config.UserInfo;
import com.thesilentnights.maalinkserver.utils.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    UserInfo userInfo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        // 检查header中的token
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token is required");
            return false;
        }

        if (Auth.checkToken(token, userInfo.getStoredUserName() + userInfo.getStoredPassword())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Invalid token");
            return false;
        }

        return true;
    }
}
