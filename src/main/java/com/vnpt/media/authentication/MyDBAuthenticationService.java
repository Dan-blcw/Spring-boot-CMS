/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.authentication;

import com.vnpt.media.dao.UsersDAO;
import com.vnpt.media.entity.Users;
import com.vnpt.media.utils.Constants;
import com.vnpt.media.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author vnpt2
 */
@Service
public class MyDBAuthenticationService implements UserDetailsService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UsersDAO usersDAO;

    private final Logger LOGGER = Logger.getLogger(MyDBAuthenticationService.class);

    @Override
    public UserDetails loadUserByUsername(String userName) {
        try {
            Users users = usersDAO.findUsersByUsernameActive(userName);
            request.getSession().setAttribute("auth", users);
            request.getSession().setAttribute("UPDATE_ROLE_" + users.getUsername(), false);
            if (users == null) {
                throw new UsernameNotFoundException("User " //
                        + userName + " was not found in the database");
            }
            List<GrantedAuthority> grantList = new ArrayList<>();
            if (users.getGroups() != null) {
                users.getGroups().getListRoles().forEach((role) -> {
                    grantList.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
                });
            }
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;

            UserDetails userDetails = (UserDetails) new User(userName,
                    users.getPassword(), enabled, accountNonExpired, //
                    credentialsNonExpired, accountNonLocked, grantList);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return userDetails;
        } catch (UsernameNotFoundException e) {
            LOGGER.error("Có lỗi xảy ra.", e.fillInStackTrace());
        }
        return null;
    }
}
