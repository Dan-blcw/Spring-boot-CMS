/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vnpt.media.controller;

/**
 *
 * @author PHU MINH HUONG
 */
import com.google.gson.Gson;
import com.vnpt.media.dao.ApiDAO;
import com.vnpt.media.dao.ApiosfDAO;
import com.vnpt.media.entity.Api;
import com.vnpt.media.entity.Users;
import com.vnpt.media.model.ApiInfo;
import com.vnpt.media.utils.Constants;
import com.vnpt.media.utils.Utils;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

@Controller
@EnableWebMvc
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiDAO apiDao;
    
    private final Logger LOGGER = Logger.getLogger(ApiController.class);

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ApiInfo detailAPI(@RequestParam(value = "id",defaultValue = "0") Long id, Model model, HttpServletRequest request){
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return null;
        }
        ApiInfo obj = apiDao.findApiInfo(id);
        return obj;
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String list(@RequestParam(value = "filter", defaultValue = "") String filter,
            @RequestParam(value = "status", defaultValue = "-1") Integer status,
            Model model, HttpServletRequest request) {
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return "redirect:/login";
        }
        // Trim Filter
        filter = filter.trim();
        List<ApiInfo> list = apiDao.listAPIInfo(filter, status);
        model.addAttribute("list", list);
        model.addAttribute("status", status);
        return "api/api_list";
    }

//--
    @RequestMapping(value = {"/insert"}, method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String insertAPI(@RequestBody ApiInfo apiInfo, Model model, BindingResult result, HttpServletRequest request) {
        LOGGER.info("Parameter:: " + new Gson().toJson(apiInfo));
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }
        if (result.hasErrors()) {
            return Constants.BINDDING_DATA_ERROR + "|error";
        }
        try {
            apiInfo.setUsercreate(users.getUsername());
            return apiDao.insertAPI(apiInfo);
        } catch (Exception e) {
            String message = e.getMessage();
            return message + " |error";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces =  "application/json; charset=utf-8")
    public String deleteAPI(Model model, @RequestParam(value = "id",defaultValue = "0") Long id, HttpServletRequest request){
        LOGGER.info("Parameter:: " + id);
        Users users =  Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }
        try{
            return apiDao.deleteAPI(id);
        } catch (Exception e){
            LOGGER.error("Co loi xay ra: ", e);
            return "co loi xay ra   |error ";
        }
    }
    
    @RequestMapping(value = {"/update"}, method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateAPI(@RequestBody ApiInfo apiInfo, Model model, BindingResult result, HttpServletRequest request) {
        // lỗi logger
//        LOGGER.info("Parameter:: " + new Gson().toJson(apiInfo));
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }
        if (result.hasErrors()) {
            return Constants.BINDDING_DATA_ERROR + "|error";
        }
        try {
            return apiDao.updateAPI(apiInfo);
        } catch (Exception e) {
            return e.getMessage() + "|error";
        }
    }


    @RequestMapping(value = "/active", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String activeAPI(Model model, @RequestParam(value = "id", defaultValue = "0") Long id, HttpServletRequest request) {
        LOGGER.info("Parameter:: " + id);
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }
        try {
            return apiDao.active(id);
        } catch (Exception e) {
            LOGGER.error("Có lỗi xảy ra: ", e);
            return "Có lỗi xảy ra.  |error";
        }
    }
    @RequestMapping(value = "/lock",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String lockAPI(Model model, @RequestParam(value = "id", defaultValue = "0") Long id, HttpServletRequest request) {
        LOGGER.info("Parameter:: " + id);
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }
        try {
            return apiDao.lock(id);
        } catch (Exception e) {
            LOGGER.error("Có lỗi xảy ra: ", e);
            return "Có lỗi xảy ra. |error";
        }
    }

    @RequestMapping(value = "/unlock",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String unlockAPI(Model model, @RequestParam(value = "id", defaultValue = "0") Long id, HttpServletRequest request) {
        LOGGER.info("Parameter:: " + id);
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }
        try {
            return apiDao.unlock(id);
        } catch (Exception e) {
            LOGGER.error("Có lỗi xảy ra: ", e);
            return "Có lỗi xảy ra. |error";
        }
    }
}
