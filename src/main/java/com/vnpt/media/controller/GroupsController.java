package com.vnpt.media.controller;

import com.google.gson.Gson;
import com.vnpt.media.dao.GroupsDAO;
import com.vnpt.media.dao.RolesDAO;
import com.vnpt.media.entity.Roles;
import com.vnpt.media.entity.Users;
import com.vnpt.media.model.GroupsInfo;
import com.vnpt.media.model.RolesInfo;
import com.vnpt.media.utils.Constants;
import com.vnpt.media.utils.Utils;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/groups")
public class GroupsController {

    @Autowired
    private GroupsDAO groupsDAO;

    @Autowired
    private RolesDAO rolesDAO;

    private final Logger LOGGER = Logger.getLogger(GroupsController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String list(@RequestParam(value = "groupName", defaultValue = "") String groupName,
            Model model, HttpServletRequest request) {
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return "redirect:/login";
        }
        // Trim Filter
        groupName = groupName.trim();

        List<GroupsInfo> list = groupsDAO.listGroupsInfo(groupName);
        model.addAttribute("list", list);
        model.addAttribute("groupName", groupName);
        return "groups/groups_list";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public GroupsInfo detail(@RequestParam(value = "id", defaultValue = "0") Long id, Model model, HttpServletRequest request) {
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return null;
        }

        return groupsDAO.findGroupsInfo(id);
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String update(@RequestBody GroupsInfo info, Model model, HttpServletRequest request) throws UnknownHostException {
        LOGGER.info("Parameter:: " + new Gson().toJson(info));
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }

        try {
            return groupsDAO.update(info);
        } catch (Exception e) {
            LOGGER.error("Có lỗi xảy ra: ", e.fillInStackTrace());
            return "Có lỗi xảy ra |error";
        }
    }

    @RequestMapping(value = {"/insert"}, method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String insert(@RequestBody GroupsInfo info, Model model, HttpServletRequest request) throws UnknownHostException {
        LOGGER.info("Parameter:: " + new Gson().toJson(info));
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }

        try {
            return groupsDAO.insert(info);
        } catch (Exception e) {
            LOGGER.error("Có lỗi xảy ra: ", e.fillInStackTrace());
            return "Có lỗi xảy ra |error";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String delete(Model model, @RequestParam(value = "id", defaultValue = "0") Long id, HttpServletRequest request) throws UnknownHostException {
        LOGGER.info("Parameter:: Id=" + id);
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return Constants.FINISH_SESSION + "|error";
        }
        try {
            return groupsDAO.delete(id);
        } catch (Exception e) {
            LOGGER.error("Có lỗi xảy ra: ", e);
            String message = e.getMessage();
            return message + " |error";
        }
    }

    @RequestMapping(value = "/listRoles", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String listRoles(@RequestParam(value = "id", defaultValue = "0") Long id, Model model, HttpServletRequest request) {
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return "redirect:/login";
        }

        GroupsInfo groupsInfo = groupsDAO.findGroupsInfo(id);
        model.addAttribute("Groups", groupsInfo);

//        List<RolesInfo> listRolesAll = rolesDAO.listRolesInfo();
        List<Roles> listRolesAll2 = rolesDAO.getTreeRoles();

        List<RolesInfo> listRolesByGroupId = groupsInfo.getListRoles();

//        for (RolesInfo rolesAll : listRolesAll) {
//            rolesAll.setAssignGroup(Boolean.FALSE);
//            for (RolesInfo roles : listRolesByGroupId) {
//                if (Objects.equals(rolesAll.getId(), roles.getId())) {
//                    rolesAll.setAssignGroup(Boolean.TRUE);
//                    break;
//                }
//            }
//        }

        for (Roles rolesAll : listRolesAll2) {
            rolesAll.setAssignGroup(Boolean.FALSE);
            for (RolesInfo roles : listRolesByGroupId) {
                if (Objects.equals(rolesAll.getId(), roles.getId())) {
                    rolesAll.setAssignGroup(Boolean.TRUE);
                    break;
                }
            }
            // con của role
            for (Roles role3 : rolesAll.getListRoles()) {
                role3.setAssignGroup(Boolean.FALSE);
                for (RolesInfo roles : listRolesByGroupId) {
                    if (Objects.equals(role3.getId(), roles.getId())) {
                        role3.setAssignGroup(Boolean.TRUE);
                        break;
                    }
                }
            }
        }

//        model.addAttribute("listRoles", listRolesAll);
        model.addAttribute("listRoles2", listRolesAll2);
        return "groups/group_role";
    }

    @RequestMapping(value = "/updateListRole", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateListRole(@RequestBody GroupsInfo info, Model model, HttpServletRequest request) throws UnknownHostException {
        LOGGER.info("Parameter:: " + new Gson().toJson(info));
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return "Chưa đăng nhập";
        }

        try {
            return groupsDAO.updateListRoles(info.getListRoles(), info.getId(), request);
        } catch (Exception e) {
            LOGGER.error("Có lỗi xảy ra: ", e);
            String message = e.getMessage();
            return message + " |error";
        }

    }

    @RequestMapping(value = "/updateListUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateListUser(@RequestBody GroupsInfo info, Model model, HttpServletRequest request) throws UnknownHostException {
        LOGGER.info("Parameter:: " + new Gson().toJson(info));
        Users users = Utils.getUsersInSession(request);
        if (users == null) {
            return "Chưa đăng nhập";
        }

        try {
            return groupsDAO.updateListUsers(info.getListUsers(), info.getId());
        } catch (Exception e) {
            LOGGER.error("Có lỗi xảy ra: ", e);
            String message = e.getMessage();
            return message + " |error";
        }
    }

}
