//package com.employee.employee_crud.helpers;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//@Component
//@RequiredArgsConstructor
//public class RequestInterceptor implements HandlerInterceptor {
//    private final JWTHelper jwtUtil;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//        }
//
//        String token = authorizationHeader.substring(7); // Extract token from "Bearer {token}"
//        String username = jwtUtil.extractUsername(token);
//
//        if (username == null || !jwtUtil.validateToken(token, username)) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//        }
//
//        return true;
//    }
//}
