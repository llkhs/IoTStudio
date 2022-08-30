package org.example.IoTStudio.interceptor;

import io.jsonwebtoken.Claims;
import org.example.IoTStudio.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//全局 token 校验
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public TokenInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    //通过拦截器对请求头进行校验
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)) {
            if (header.startsWith("Bearer ")) {
                //获得token
                String token = header.substring(7);
                //验证token
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String Key = (String) claims.get("key");
                    if (Key != null) {
                        request.setAttribute("username",claims.getId());
                        request.setAttribute("key",Key);
                        System.out.println("--------------------decode succeeded");
                        return true;
                    } else {
                        throw new BadCredentialsException("令牌已失效");
                    }
                } catch (Exception e) {
                    throw new BadCredentialsException("令牌已失效");
                }
            }
        }
        throw new AuthenticationCredentialsNotFoundException("请先登录");
    }
}