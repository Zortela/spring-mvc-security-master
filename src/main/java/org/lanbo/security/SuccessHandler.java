package org.lanbo.security;

import org.lanbo.dao.UserDAO;
import org.lanbo.model.User;
import org.lanbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        boolean admin = false;
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_ADMIN".equals(auth.getAuthority())) {
                admin = true;
            }
        }

        User user = (User) authentication.getPrincipal();
        if (admin) {
            httpServletResponse.sendRedirect("/admin");
        } else {
            httpServletResponse.sendRedirect("/user/" + user.getId());
        }
    }
}


