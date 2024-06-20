package com.vnpt.media.dao;

import com.vnpt.media.entity.Roles;
import com.vnpt.media.model.RolesInfo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vnpt2
 */
@Transactional
@Repository
public class RolesDAO {

    @PersistenceContext
    public EntityManager entityManager;

    public List<RolesInfo> listRolesInfo() {
        String sql = "Select new " + RolesInfo.class.getName()//
                + "(c.id, c.name, c.description, "
                + " c.url, c.parentRoles.id, "
                + "c.parentRoles.name) " + " from "
                + Roles.class.getName() + " c  left join c.parentRoles "
                + " order by c.name";
        Query q = entityManager.createQuery(sql);
        return q.getResultList();
    }

    public Roles findRoles(Long id) {
        return entityManager.find(Roles.class, id);
    }

    public List<Roles> getTreeRoles() {
        Query q = entityManager.
                createNativeQuery("SELECT *\n"
                        + "FROM   CMS_ROLES where parent_id is null \n"
                        + "START WITH PARENT_ID IS NULL\n"
                        + "CONNECT BY PRIOR ID = PARENT_ID\n"
                        + "ORDER SIBLINGS BY name ", Roles.class);
        List<Roles> list = q.getResultList();
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        list.forEach(role -> {
            // load LAZZY
            role.getListRoles().size();
        });
        return list;
    }

}
