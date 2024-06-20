/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.interceptor;

//import com.vnpt.media.crosssale.entity.Users;
import com.vnpt.media.entity.Users;
import com.vnpt.media.utils.Utils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author vnpt2
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    private Logger loggerInfo = Logger.getLogger(HandlerInterceptorAdapter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Users auth = Utils.getUsersInSession(request);
        String userName = "";
        if (auth != null) {
            userName = auth.getUsername();
        }
        long startTime = System.currentTimeMillis();
        loggerInfo.info("Request URL::" + request.getServletPath()
                + ":: Start Time=" + System.currentTimeMillis() + ":: UserName=" + userName + ":: Method=" + request.getMethod());
        request.setAttribute("startTime", startTime);
        if (request.getMethod().equals("GET")) {
            loggerInfo.info("QueryGET: " + request.getQueryString());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        long startTime = (Long) request.getAttribute("startTime");
        loggerInfo.info("Request URL::" + request.getServletPath()
                + ":: End Time=" + System.currentTimeMillis());
        loggerInfo.info("Request URL::" + request.getServletPath()
                + ":: Time Taken=" + (System.currentTimeMillis() - startTime) + "\n");
    }
}
