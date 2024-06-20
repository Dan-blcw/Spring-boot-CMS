/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vnpt.media.dao;

/**
 *
 * @author PHU MINH HUONG
 */

import com.vnpt.media.entity.Api;
import com.vnpt.media.model.ApiInfo;
import com.vnpt.media.utils.Constants;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class ApiDAO  {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ApiInfo> listAPIInfo(String filter, Integer status){
        String sql = "Select new " + ApiInfo.class.getName()
                + "(c.id, c.apiname,c.authentication ,c.request, "
                + " c.response, c.createTime, c.status, c.usercreate) "
                + " from "+ Api.class.getName()+ " c where "
                + " (lower(c.apiname) like :filter "
                + " or lower(c.usercreate) like :filter )"
                + " and (c.status = :status or :status = -1) " +
                "order by c.id desc";
        Query q = entityManager.createQuery(sql);
        q.setParameter("filter", "%" + filter.toLowerCase() + "%");
        q.setParameter("status", status);
        return q.getResultList();
    }
    
    public List<Api> findAll() {
        Query q = entityManager.
                createNativeQuery("Select * from CMS_API  ", Api.class);
        List<Api> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        entityManager.flush();
        return list;    
    }

    public Api getDetail(String apiname){
        Query q = entityManager.
                createNativeQuery("Select * from CMS_API  where lower(apiname) = lower(:apiname) ", Api.class);
        q.setParameter("apiname", apiname);
        List<Api> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        list.get(0).setStatus(1);
        entityManager.flush();
        return list.get(0);
    }
    public Api findApiByApinameActive(String apiname) {
        Query q = entityManager.
                createNativeQuery("Select * from CMS_API  where lower(apiname) = lower(:apiname) "
                        + "  or status = 1", Api.class);
        q.setParameter("apiname", apiname);
        List<Api> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        list.get(0).setStatus(1);
        entityManager.flush();
        return list.get(0);
    }

    public String insertAPI(ApiInfo doInfo){
        Api aPi = new Api();
        if(doInfo == null){
            return Constants.API_NOT_EXIST + "|error";
        }
        aPi.setApiname( doInfo.getApiname());
        aPi.setStatus(0);
        aPi.setAuthentication(doInfo.getAuthentication());
        aPi.setRequest(doInfo.getRequest());
        aPi.setResponse(doInfo.getResponse());
        aPi.setCreateTime(new Date());
        aPi.setUsercreate(doInfo.getUsercreate());

        Api flagAPI = findApiByApinameAll(doInfo.getApiname());
        if(flagAPI != null){
            return Constants.API_EXIST + "?|error";
        }
        entityManager.persist(aPi);
        entityManager.flush();
        return Constants.INSERT_DATA_SUCCESS + "?|success";
    }
    
    public void insertVoidAPI(ApiInfo doInfo){
            Api aPi = new Api();
            if(doInfo == null){
                return ;
            }
            aPi.setApiname( doInfo.getApiname());
            aPi.setStatus(0);
            aPi.setAuthentication(doInfo.getAuthentication());
            aPi.setRequest(doInfo.getRequest());
            aPi.setResponse(doInfo.getResponse());
            aPi.setCreateTime(new Date());
            aPi.setUsercreate(doInfo.getUsercreate());
            Api flagAPI = findApiByApinameAll(doInfo.getApiname());
            if(flagAPI != null){
                return ;
            }
            entityManager.persist(aPi);
            entityManager.flush();
    }
    public boolean isExistAPIName(String name){
        Api flagAPI = findApiByApinameAll(name);
        if(flagAPI != null){
            return true;
        }
        return false;
    }


    public Api findApiByApinameAll(String apiname){
        Query q = entityManager.createNativeQuery(
                "SELECT * FROM CMS_API WHERE lower(apiname) = lower(:apiname)", Api.class);
        q.setParameter("apiname", apiname);
        List<Api> listApi = q.getResultList();
        if(listApi.isEmpty()){
            return null;
        }
        
        return  listApi.get(0);
    }

    public String updateAPI(ApiInfo doInfo){
        Api updateAPI = null;
        if(doInfo !=  null){
            updateAPI = findAPI(doInfo.getId());
        }
        if(updateAPI == null){
            return  Constants.API_NOT_EXIST + "?| Error";
        }
        updateAPI.setAuthentication(doInfo.getAuthentication());
        updateAPI.setRequest(doInfo.getRequest());
        updateAPI.setResponse(doInfo.getResponse());
        return Constants.UPDATE_DATA_SUCCESS + "?| SUCCESS";
    }

    public Api findAPI(Long id){
        if(id == null){
            return null;
        }
        Api api = entityManager.find(Api.class,id);
        entityManager.flush();
        return api;
    }
    public ApiInfo findApiInfo(Long id) {
        Api api = findAPI(id);
        if (api == null) {
            return new ApiInfo();
        }

        return new ApiInfo(id, api.getApiname(), 
                api.getAuthentication(),
                api.getRequest(), 
                api.getResponse(),
                api.getCreateTime(),
                api.getStatus(), 
                api.getUsercreate()
        );
    }

    public String deleteAPI(Long id){
        Api api = findAPI(id);
        if(api == null){
            return Constants.API_NOT_EXIST + "?| Error";
        }
        if(api.getStatus() != null && api.getStatus() == 1){
            return  "Khong the xoa vi API hien dang active | Error";
        }
        entityManager.remove(api);
        return Constants.DELETE_DATA_SUCCESS + "| Success";
    }

    public String active(Long id){
        Api api = findAPI(id);
        if(api == null){
            return Constants.API_NOT_EXIST + "?| Error";
        }
        if(api.getStatus() == 1){
            return "Tai khoan nay hien tai da duoc active";
        }
        api.setStatus(1);
        entityManager.flush();
        return "Active thanh cong |Success";
    }

    public String lock(Long id) {
        Api api = findAPI(id);
        if (api == null) {
            return Constants.API_NOT_EXIST + "|error";
        }

        if (api.getStatus() == 0) {
            return "Tài khoản này đã được lock|success";
        }
        api.setStatus(0);
        entityManager.flush();
        return "Lock thành công|success";
    }

    public String unlock(Long id) {
        Api api = findAPI(id);
        if (api == null) {
            return Constants.API_NOT_EXIST + "|error";
        }

        if (api.getStatus() == 1) {
            return "Tài khoản này đã được unlock|success";
        }
        api.setStatus(1);
        entityManager.flush();
        return "Unlock thành công|success";
    }



}
