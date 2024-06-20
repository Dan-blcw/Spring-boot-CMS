/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.controller;

import com.vnpt.media.entity.Users;
import com.vnpt.media.utils.Utils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author vnpt2
 */
@Controller
// Cần thiết để sử dụng RedirectAttributes
@EnableWebMvc
public class MainController {

    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        Utils.removeUsersInSession(request);
        return "login";
    }

    @RequestMapping(value = {"error/403"}, produces = "application/json; charset=utf-8")
    public String error403(HttpServletRequest request) {
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return "redirect:/login";
        }
        return "403";
    }

    @RequestMapping(value = {"error/404"}, produces = "application/json; charset=utf-8")
    public String error404(HttpServletRequest request) {
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return "redirect:/login";
        }
        return "404";
    }

    @RequestMapping(value = {"error/500"}, produces = "application/json; charset=utf-8")
    public String error500(HttpServletRequest request) {
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return "redirect:/login";
        }
        return "500";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Utils.removeUsersInSession(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/checkCaptcha", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean checkCaptcha(@RequestParam(value = "grecaptcha", defaultValue = "0") String captcha, HttpServletRequest request) {
        return Utils.verifyCaptcha(captcha);
    }

    @RequestMapping(value = {"/subs/addFile"}, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String addFileSubs(HttpServletRequest request) {
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return "redirect:/login";
        }
        return "subs/subs_add_file";
    }

}
