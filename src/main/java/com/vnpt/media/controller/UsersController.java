package com.vnpt.media.controller;

import com.google.gson.Gson;
import com.vnpt.media.dao.GroupsDAO;
import com.vnpt.media.dao.UsersDAO;
import com.vnpt.media.entity.Users;
import com.vnpt.media.model.GroupsInfo;
import com.vnpt.media.model.UsersInfo;
import com.vnpt.media.utils.Constants;
import com.vnpt.media.utils.Utils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private GroupsDAO groupsDAO;

    private final Logger LOGGER = Logger.getLogger(UsersController.class);

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
        List<UsersInfo> list = usersDAO.listUsersInfo(filter, status);
        model.addAttribute("list", list);
        model.addAttribute("status", status);
        List<GroupsInfo> listGroups = groupsDAO.listGroupsInfo("");
        model.addAttribute("listGroups", listGroups);
        return "users/users_list";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public UsersInfo detail(@RequestParam(value = "id", defaultValue = "0") Long id, Model model, HttpServletRequest request) {
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return null;
        }
        UsersInfo obj = usersDAO.findUsersInfo(id);
        return obj;
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String update(@RequestBody UsersInfo usersInfo, Model model, BindingResult result, HttpServletRequest request) {
        LOGGER.info("Parameter:: " + new Gson().toJson(usersInfo));
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }

        if (result.hasErrors()) {
            return Constants.BINDDING_DATA_ERROR + "|error";
        }
        try {
            return usersDAO.update(usersInfo);
        } catch (Exception e) {

            if (e.getMessage().contains("CMS_USERS_UK1")) {
                return "Email đã tồn tại trong hệ thống |error";
            }

            if (e.getMessage().contains("CMS_USERS_UK2")) {
                return "Số điện thoại đã tồn tại trong hệ thống |error";
            }

            String message = e.getMessage();
            return message + " |error";
        }
    }

    @RequestMapping(value = {"/insert"}, method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String insert(@RequestBody UsersInfo usersInfo, Model model, BindingResult result, HttpServletRequest request) {
        LOGGER.info("Parameter:: " + new Gson().toJson(usersInfo));
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }
        if (result.hasErrors()) {
            return Constants.BINDDING_DATA_ERROR + "|error";
        }
        try {
            return usersDAO.insert(usersInfo);
        } catch (Exception e) {
            if (e.getMessage().contains("CMS_USERS_UK1")) {
                return "Email đã tồn tại trong hệ thống |error";
            }
            if (e.getMessage().contains("CMS_USERS_UK2")) {
                return "Số điện thoại đã tồn tại trong hệ thống |error";
            }
            String message = e.getMessage();
            return message + " |error";
        }
    }

    @RequestMapping(value = "/updatePersonal", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updatePersonal(@RequestBody UsersInfo usersInfo, Model model, BindingResult result, HttpServletRequest request) {
        LOGGER.info("Parameter:: " + new Gson().toJson(usersInfo));
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }
        if (result.hasErrors()) {
            return Constants.BINDDING_DATA_ERROR + "|error";
        }
        try {
            // ANTT
            usersInfo.setId(users.getId());
            usersInfo.setUsername(users.getUsername());
            return usersDAO.updatePersonal(usersInfo);
        } catch (Exception e) {
            String message = e.getMessage();
            return message + " |error";
        }
    }

    @RequestMapping(value = "/updatePersonal", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String updateInfo(HttpServletRequest request, Model model) {
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return "redirect:/login";
        }
        Long id = users.getId();
        users = usersDAO.findUsers(id);
        model.addAttribute("usersDetail", users);
        return "users/users_edit_info";
    }

    @RequestMapping(value = "/changePass", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String changePass(HttpServletRequest request, Model model) {
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", users.getUsername());
        return "users/change_pass";
    }

    @RequestMapping(value = "/changePass", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String changePass(HttpServletRequest request, Model model,
            @RequestParam(value = "oldPass", defaultValue = "") String oldPass,
            @RequestParam(value = "newPass", defaultValue = "") String newPass) {
        LOGGER.info("Parameter:: oldPass=" + oldPass + ", newPass=" + newPass);
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }
        return usersDAO.changePass(users.getId(), newPass, oldPass);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String delete(Model model, @RequestParam(value = "id", defaultValue = "0") Long id, HttpServletRequest request) {
        LOGGER.info("Parameter:: " + id);
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }
        try {
            return usersDAO.delete(id);
        } catch (Exception e) {
            LOGGER.error("Có lỗi xảy ra: ", e);
            return "Có lỗi xảy ra. |error";
        }
    }

    @RequestMapping(value = "/active", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String active(Model model, @RequestParam(value = "id", defaultValue = "0") Long id, HttpServletRequest request) {
        LOGGER.info("Parameter:: " + id);
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }
        try {
            return usersDAO.active(id);
        } catch (Exception e) {
            LOGGER.error("Có lỗi xảy ra: ", e);
            return "Có lỗi xảy ra.  |error";
        }
    }

    @RequestMapping(value = "/lock", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String lock(Model model, @RequestParam(value = "id", defaultValue = "0") Long id, HttpServletRequest request) {
        LOGGER.info("Parameter:: " + id);
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }
        try {
            return usersDAO.lock(id);
        } catch (Exception e) {
            LOGGER.error("Có lỗi xảy ra: ", e);
            return "Có lỗi xảy ra. |error";
        }
    }

}
