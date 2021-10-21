package com.example.ppmtool.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.ppmtool.exceptions.InvalidLoginResponse;
import com.google.gson.Gson;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        InvalidLoginResponse invalidLoginResponse = new InvalidLoginResponse();
        String jsonLoginResponse = new Gson().toJson(invalidLoginResponse);

        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().print(jsonLoginResponse);
    }
    
}
