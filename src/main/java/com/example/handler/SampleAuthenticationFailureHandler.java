package com.example.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class SampleAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	@Override
    public void onAuthenticationFailure(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException authenticationException)
                    throws IOException, ServletException {

        String errorId = "";
        // ExceptionからエラーIDをセットする
        if(authenticationException instanceof BadCredentialsException){
            errorId = "ERROO1";
        }

        // ログイン画面にリダイレクトする
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login"
                + "?error=" + errorId);
    }
}
