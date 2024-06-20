/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vnpt.media.controller;


import com.vnpt.media.dao.ApiosfDAO;
import com.vnpt.media.entity.Users;
import com.vnpt.media.model.APIOSFInfo;
import com.vnpt.media.model.ApiInfo;
import com.vnpt.media.model.SFInfo;
import com.vnpt.media.utils.Constants;
import com.vnpt.media.utils.Utils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author PHU MINH HUONG
 */

@Controller
@EnableWebMvc
@RequestMapping("/storagefile")
public class SFController {
    @Autowired
    private ApiosfDAO apiosfDao;
    
    private final Logger LOGGER = Logger.getLogger(SFController.class);
    
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
        List<SFInfo> list = apiosfDao.listSFInfo(filter, status);
        model.addAttribute("list", list);
        model.addAttribute("status", status);
        return "sf/api_sf_list";
    }
   
//    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
//    public List<APIOSFInfo> detailSF(@RequestParam(value = "id",defaultValue = "0") Long id, Model model, HttpServletRequest request {
//        Users users = Utils.getUsersInSession(request);
//        if (users == null) {
//            return ;
//        }
//        // Trim Filter
//        filter = filter.trim();
//        List<ApiosfDAO> list = apiosfDao.getDetailAPIOSF(filter, status);
//        model.addAttribute("list", list);
//        model.addAttribute("status", status);
//    }
//    
    
    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String detailAPI(@RequestParam(value = "id",defaultValue = "0") Long id, Model model, HttpServletRequest request){
//        Users users = Utils.getUsersInSession(request);
//        if (users == null) {
//            return null;
//        }
//        APIOSFInfo list = apiosfDao.getDetailAPIOSF(id);
//        model.addAttribute("list", list);
        
                Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return "redirect:/login";
        }
        // Trim Filter
//        filter = filter.trim();
        List<APIOSFInfo> list = apiosfDao.getDetailAPIOSF(id);
        model.addAttribute("list", list);
//        return list;
        return "sf/api_history_details";
    }
    
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces =  "application/json; charset=utf-8")
    public String deleteSF(Model model, @RequestParam(value = "id",defaultValue = "0") Long id, HttpServletRequest request){
        LOGGER.info("Parameter:: " + id);
        Users users =  Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }
        try{
            return apiosfDao.deleteSF(id);
        } catch (Exception e){
            LOGGER.error("Co loi xay ra: ", e);
            return "co loi xay ra   |error ";
        }
    }

    
}
