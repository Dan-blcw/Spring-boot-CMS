package com.vnpt.media.dao;

import com.vnpt.media.entity.Groups;
import com.vnpt.media.entity.Roles;
import com.vnpt.media.entity.Users;
import com.vnpt.media.model.GroupsInfo;
import com.vnpt.media.model.RolesInfo;
import com.vnpt.media.model.UsersInfo;
import com.vnpt.media.utils.Constants;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vnpt2
 */
@Transactional
@Repository
public class GroupsDAO {

    @PersistenceContext
    public EntityManager entityManager;

    public GroupsInfo getDetail(Long id) {
        String sql = "Select new " + GroupsInfo.class.getName()//
                + "(c.id, c.name, c.description, "
                + " c.createTime, c.updateTime) " + " from "
                + Groups.class.getName() + " c where c.id = :id ";
        Query q = entityManager.createQuery(sql);
        q.setParameter("id", id);
        List list = q.getResultList();
        if (list.isEmpty()) {
            return new GroupsInfo();
        }
        return (GroupsInfo) list.get(0);
    }

    public List<GroupsInfo> listGroupsInfo(String filter) {
        String sql = "Select new " + GroupsInfo.class.getName()//
                + "(c.id, c.name, c.description, "
                + " c.createTime, c.updateTime) " + " from "
                + Groups.class.getName() + " c where "
                + " lower(c.name) like :filter "
                + " or lower(c.description) like :filter "
                + " order by c.id desc";
        Query q = entityManager.createQuery(sql);
        q.setParameter("filter", "%" + filter.toLowerCase() + "%");
        return q.getResultList();
    }

    public String update(GroupsInfo info) {
        Groups groups = null;
        if (info.getId() != null) {
            groups = this.findGroups(info.getId());
        }
        if (groups == null) {
            return "Nhóm ko tồn tại|error";
        }
        groups.setName(info.getName());
        groups.setDescription(info.getDescription());
        groups.setUpdateTime(new Date());

        entityManager.flush();
        return Constants.UPDATE_DATA_SUCCESS + "|success";
    }

    public String insert(GroupsInfo info) {
        Groups groups = new Groups();
        groups.setCreateTime(new Date());
        groups.setName(info.getName());
        groups.setDescription(info.getDescription());
        groups.setUpdateTime(new Date());
        entityManager.persist(groups);
        entityManager.flush();
        return Constants.INSERT_DATA_SUCCESS + "|success";

    }

    public GroupsInfo findGroupsInfo(Long id) {
        Groups groups = findGroups(id);
        if (groups == null) {
            return new GroupsInfo();
        }
        List<RolesInfo> listRoles = new ArrayList<>();
        groups.getListRoles().stream().map((roles) -> {
            RolesInfo info = new RolesInfo();
            info.setId(roles.getId());
            info.setName(roles.getName());
            info.setUrl(roles.getUrl());
            return info;
        }).forEachOrdered((info) -> {
            listRoles.add(info);
        });

        List<UsersInfo> listUsers = new ArrayList<>();
        groups.getListUsers().stream().map((users) -> {
            UsersInfo info = new UsersInfo();
            info.setId(users.getId());
            info.setUsername(users.getUsername());
            return info;
        }).forEachOrdered((info) -> {
            listUsers.add(info);
        });

        return new GroupsInfo(id, groups.getName(), groups.getDescription(),
                groups.getCreateTime(), groups.getUpdateTime(), listRoles, listUsers);
    }

    public Groups findGroups(Long id) {
        return entityManager.find(Groups.class, id);
    }

    public String delete(Long id) {
        Groups groups = findGroups(id);
        if (groups == null) {
            return Constants.GROUP_NOT_EXIST + "|error";
        }
        if (!groups.getListUsers().isEmpty()) {
            return Constants.GROUP_USER_FK + "|error";
        }
        entityManager.remove(groups);
        return Constants.DELETE_DATA_SUCCESS + "|success";
    }

    public String updateListRoles(List<RolesInfo> listRolesInfo, Long id, HttpServletRequest request) {
        Groups groups;
        groups = this.findGroups(id);
        if (groups == null) {
            return "Không tồn tại nhóm quyền |error";
        }

        List<Roles> listRoles = new ArrayList<>();
        if (listRolesInfo != null) {
            listRolesInfo.stream().map((rolesInfo) -> {
                Roles roles = new Roles();
                roles.setId(rolesInfo.getId());
                return roles;
            }).forEachOrdered((roles) -> {
                listRoles.add(roles);
            });
        }

        groups.setListRoles(listRoles);
        groups.setUpdateTime(new Date());

        groups.getListUsers().forEach(listUser -> {
            request.getSession().setAttribute("UPDATE_ROLE_" + listUser.getUsername(), true);
        });

        entityManager.flush();
        return "Gán quyền cho nhóm thành công|success";

    }

    public String updateListUsers(List<UsersInfo> listUsersInfo, Long id) {
        Groups groups;
        groups = this.findGroups(id);
        if (groups == null) {
            return "Không tồn tại nhóm quyền |error";
        }

        List<Users> listUsers = new ArrayList<>();
        if (listUsersInfo != null) {
            listUsersInfo.stream().map((usersInfo) -> {
                Users users = new Users();
                users.setId(usersInfo.getId());
                return users;
            }).forEachOrdered((users) -> {
                listUsers.add(users);
            });
        }
        System.out.println("sizze: " + listUsers.size());
        groups.setListUsers(listUsers);
        groups.setUpdateTime(new Date());

        entityManager.flush();
        return "Gán người dùng cho nhóm thành công|success";
    }
}
