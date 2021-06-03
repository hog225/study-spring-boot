package org.yg.practice.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class FailureHandler extends SimpleUrlAuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        String failureUrl = "/login?error=invalid";
        this.setDefaultFailureUrl(failureUrl);
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("password");
        request.getSession().removeAttribute("mfa");

        super.onAuthenticationFailure(request, response, exception);
    }
}
