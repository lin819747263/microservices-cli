package com.linmsen.user.handlder;

import com.alibaba.fastjson.JSONObject;
import com.linmsen.core.vo.CommonResult;
import com.linmsen.user.JwtUtil;
import com.linmsen.user.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
        UserDetails sysUser = (UserDetails)authentication.getPrincipal();
        JwtUtil.generateToken(sysUser.getUsername());
        httpServletResponse.getWriter().print(JSONObject.toJSON(CommonResult.success(JwtUtil.generateToken(sysUser.getUsername()))).toString());
    }

}
