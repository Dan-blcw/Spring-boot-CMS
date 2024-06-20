package com.vnpt.media.dao;

import com.vnpt.media.entity.Groups;
import com.vnpt.media.entity.Users;
import com.vnpt.media.model.UsersInfo;
import com.vnpt.media.utils.Constants;
import com.vnpt.media.utils.PasswordValidator;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vnpt2
 */
@Transactional
@Repository
public class UsersDAO {

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    private GroupsDAO groupsDAO;

    public List<UsersInfo> listUsersInfo(String filter, Integer status) {
        String sql = "Select new " + UsersInfo.class.getName()//
                + "(c.id, c.username, c.password, "
                + " c.email, c.phone, c.status,  "
                + " c.createTime, c.updateTime, c.groups.id, c.groups.name) " + " from "
                + Users.class.getName() + " c left join c.groups  where "
                + " (lower(c.username) like :filter "
                + " or lower(c.email) like :filter "
                + " or lower(c.phone) like :filter) and (c.status = :status or :status = -1) order by c.id desc";
        Query q = entityManager.createQuery(sql);
        q.setParameter("filter", "%" + filter.toLowerCase() + "%");
        q.setParameter("status", status);
        return q.getResultList();
    }

    public String update(UsersInfo info) {
        Users users = null;

        Groups groups = null;
        if (info.getId() != null) {
            users = this.findUsers(info.getId());
        }
        if (users == null) {
            return Constants.USERS_NOT_EXIST + "|error";
        }
        users.setEmail(info.getEmail());
        users.setPhone(info.getPhone());
        users.setUpdateTime(new Date());

        //Lay thong tin groups
        if (info.getGroupId() != null) {
            groups = groupsDAO.findGroups(info.getGroupId());
        }
        if (groups == null) {
            return Constants.GROUP_NOT_EXIST + "|error";
        }
        users.setGroups(groups);
        entityManager.flush();
        return Constants.UPDATE_DATA_SUCCESS + "|success";
    }

    public String insert(UsersInfo info) {
        Users users = new Users();
        Groups groups = null;
        users.setStatus(0);
        users.setUsername(info.getUsername());
        users.setCreateTime(new Date());
        //Lay thong tin groups
        if (info.getGroupId() != null) {
            groups = groupsDAO.findGroups(info.getGroupId());
        }
        if (groups == null) {
            return Constants.GROUP_NOT_EXIST + "|error";
        }

        if (!PasswordValidator.isValid(info.getPassword())) {
            return "Mật khẩu không thỏa mãn điều kiện|error";
        }

        users.setGroups(groups);
        users.setEmail(info.getEmail());
        users.setPhone(info.getPhone());
        users.setUpdateTime(new Date());
        
        Users users2 = findUsersByUsernameAll(info.getUsername());
        if (users2 != null) {
            return Constants.USERNAME_EXIST + "?|error";
        }
        // encode password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncode = passwordEncoder.encode(info.getPassword());
        users.setPassword(passwordEncode);
        entityManager.persist(users);
        entityManager.flush();
        return Constants.INSERT_DATA_SUCCESS + "?|success";

    }

    public UsersInfo findUsersInfo(Long id) {
        Users users = findUsers(id);
        if (users == null) {
            return new UsersInfo();
        }

        return new UsersInfo(id, users.getUsername(), users.getPassword(),
                users.getEmail(), users.getPhone(), users.getStatus(),
                users.getCreateTime(), users.getUpdateTime(),
                users.getGroups() == null ? 0 : users.getGroups().getId(),
                users.getGroups() == null ? "" : users.getGroups().getName());
    }

    public Users findUsers(Long id) {
        if (id == null) {
            return null;
        }
        Users users = entityManager.find(Users.class, id);
        entityManager.flush();
        return users;
    }

    public String delete(Long id) {
        Users users = findUsers(id);
        if (users == null) {
            return Constants.USERS_NOT_EXIST + "|error";
        }

        if (users.getStatus() != null && users.getStatus() == 1) {
            return "Không xóa được vì users đang active|error";
        }

        entityManager.remove(users);
        return Constants.DELETE_DATA_SUCCESS + "|success";
    }

    public String updateStatus(Long id, Integer status) {
        Users users = findUsers(id);
        if (users == null) {
            return Constants.USERS_NOT_EXIST + "|error";
        }
        users.setStatus(status);
        users.setUpdateTime(new Date());
        entityManager.flush();
        return "Cập nhật trạng thái thành công|success";
    }

    public Users findUsersByUsernameAll(String username) {
        Query q = entityManager.
                createNativeQuery("Select * from CMS_USERS  where lower(username) = lower(:username) ", Users.class);
        q.setParameter("username", username);
        List<Users> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        // Load LAZY nên phai lam the OK
        System.out.println("Size Role: " + list.get(0).getGroups().getListRoles().size());
        return list.get(0);
    }

    public Users findUsersByUsernameActive(String username) {
        Query q = entityManager.
                createNativeQuery("Select * from CMS_USERS  where lower(username) = lower(:username) "
                        + " and ((status =2 and UPDATE_TIME <=:updateTime) or status = 1)", Users.class);
        q.setParameter("username", username);
        q.setParameter("updateTime", DateUtils.addHours(new Date(), -1));
        List<Users> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        // Load LAZY nên phai lam the OK
        System.out.println("Size Role: " + list.get(0).getGroups().getListRoles().size());
        // Update trang thai ve active
        list.get(0).setStatus(1);
        entityManager.flush();
        return list.get(0);
    }

    public String updatePersonal(UsersInfo info) {
        Users users = null;
        if (info.getId() != null) {
            users = this.findUsers(info.getId());
        }
        if (users == null) {
            return Constants.USERS_NOT_EXIST;
        }
        users.setEmail(info.getEmail());
//        users.setPhone(info.getPhone());
        users.setUpdateTime(new Date());
        entityManager.flush();
        return Constants.UPDATE_DATA_SUCCESS + "|success";
    }

    public String changePass(Long id, String newPass, String oldPass) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println("ID: " + id);

        Users users = null;
        if (id != null) {
            users = this.findUsers(id);
        }
        if (users == null) {
            return Constants.USERS_NOT_EXIST;
        }
        if (!passwordEncoder.matches(oldPass, users.getPassword())) {
            return Constants.OLD_PASS_WRONG + "|error";
        }
        users.setPassword(passwordEncoder.encode(newPass));
        users.setUpdateTime(new Date());
        entityManager.flush();
        return Constants.CHANGE_PASS_SC + "|success";
    }

    public String resetPass(Long id, String pass) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Users users = null;
        if (id != null) {
            users = this.findUsers(id);
        }
        if (users == null) {
            return Constants.USERS_NOT_EXIST;
        }

        users.setPassword(passwordEncoder.encode(pass));
        users.setUpdateTime(new Date());
        entityManager.flush();
        return "Reset mật khẩu thành công|success";
    }

    public String active(Long id) {
        Users users = findUsers(id);
        if (users == null) {
            return Constants.USERS_NOT_EXIST + "|error";
        }

        if (users.getStatus() == 1) {
            return "Tài khoản này đã được active|success";
        }
        users.setStatus(1);
        users.setUpdateTime(new Date());
        entityManager.flush();
        return "Active thành công|success";
    }

    public String lock(Long id) {
        Users users = findUsers(id);
        if (users == null) {
            return Constants.USERS_NOT_EXIST + "|error";
        }

        if (users.getStatus() == 0) {
            return "Tài khoản này đã được lock|success";
        }
        users.setStatus(0);
        users.setUpdateTime(new Date());
        entityManager.flush();
        return "Lock thành công|success";
    }


}
