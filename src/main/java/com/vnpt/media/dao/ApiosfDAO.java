/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vnpt.media.dao;

/**
 *
 * @author PHU MINH HUONG
 */
import com.vnpt.media.entity.APIOSF;
import com.vnpt.media.entity.StorageFile;
import com.vnpt.media.model.APIOSFInfo;
import com.vnpt.media.model.ApiInfo;
import com.vnpt.media.model.SFInfo;
import com.vnpt.media.utils.Constants;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class ApiosfDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<SFInfo> listSFInfo(String filter, Integer status){
        String sql = "Select new " + SFInfo.class.getName()
                + "(c.id, c.filename,c.status ,c.createTime, "
                + " c.success, c.fail, c.userinput) "
                + " from "+ StorageFile.class.getName()+ " c where "
                + " (lower(c.filename) like :filter "
                + " or lower(c.userinput) like :filter )"
                + " and (c.status = :status or :status = -1) " +
                "order by c.id desc";
        Query q = entityManager.createQuery(sql);
        q.setParameter("filter", "%" + filter.toLowerCase() + "%");
        q.setParameter("status", status);
        return q.getResultList();
    }
    
    public List<StorageFile> findAllSF() {
        Query q = entityManager.
                createNativeQuery("Select * from CMS_SF  ", StorageFile.class);
        List<StorageFile> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        entityManager.flush();
        return list;    
    }
        
//    public StorageFile getDetailSF(String filename){
//        Query q = entityManager.
//                createNativeQuery("Select * from CMS_SF  where lower(filename) = lower(:filename) ", Api.class);
//        q.setParameter("filename", filename);
//        List<StorageFile> list = q.getResultList();
//        if (list.isEmpty()) {
//            return null;
//        }
//        list.get(0).setStatus(1);
//        entityManager.flush();
//        return list.get(0);
//    }
        
    public StorageFile findStorageFileByFilenameActive(String filename) {
        Query q = entityManager.
                createNativeQuery("Select * from CMS_SF  where lower(filename) = lower(:filename) "
                        + "  or status = 1", StorageFile.class);
        q.setParameter("filename", filename);
        List<StorageFile> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        list.get(0).setStatus(1);
        entityManager.flush();
        return list.get(0);
    }
    
    public Long findSFID(String filename){
        Query q = entityManager.createNativeQuery(
                "SELECT * FROM CMS_SF WHERE lower(filename) = lower(:filename)", StorageFile.class);
        q.setParameter("filename", filename);
        List<StorageFile> listApi = q.getResultList();
        if(listApi.isEmpty()){
            return null;
        }
        
        return  listApi.get(0).getId();
    }
    
    public void insertVoidSF(SFInfo doInfo){
        StorageFile sF = new StorageFile();
        if(doInfo == null){
            return ;
        }
        sF.setFilename(doInfo.getFilename());
//        sF.setStatus(doInfo.getStatus());
        sF.setCreateTime(doInfo.getCreateTime());
//        sF.setSuccess(doInfo.getSuccess());
//        sF.setFail(doInfo.getFail());
        sF.setUserinput(doInfo.getUserinput());

        StorageFile flagAPI = findSFByfilenameAll(doInfo.getFilename());
        if(flagAPI != null){
            return ;
        }
        entityManager.persist(sF);
        entityManager.flush();
    }
    
    public boolean isExistFILEName(String name){
        StorageFile flagAPI = findSFByfilenameAll(name);
        if(flagAPI != null){
            return true;
        }
        return false;
    }


    public StorageFile findSFByfilenameAll(String filename){
        Query q = entityManager.createNativeQuery(
                "SELECT * FROM CMS_SF WHERE lower(filename) = lower(:filename)", StorageFile.class);
        q.setParameter("filename", filename);
        List<StorageFile> listApi = q.getResultList();
        if(listApi.isEmpty()){
            return null;
        }
        
        return  listApi.get(0);
    }

//    public String updateFilename(AP doInfo){
//        StorageFile updateSF = null;
//        if(doInfo !=  null){
//            updateSF = findSF(doInfo.getId());
//        }
//        if(updateSF == null){
//            return  Constants.SF_NOT_EXIST + "?| Error";
//        }
//        updateSF.setFilename(doInfo.getFilename());
//        return Constants.UPDATE_DATA_SUCCESS + "?| SUCCESS";
//    }

    public StorageFile findSF(Long id){
        if(id == null){
            return null;
        }
        StorageFile sf = entityManager.find(StorageFile.class,id);
        entityManager.flush();
        return sf;
    }

    public SFInfo findSFInfo(Long id) {
        StorageFile sf = findSF(id);
        if (sf == null) {
            return new SFInfo();
        }

        return new SFInfo(id, sf.getFilename(), 
                sf.getStatus(),
                sf.getCreateTime(), 
                sf.getSuccess(),
                sf.getFail(),
                sf.getUserinput()
        );
    }

    public String deleteSF(Long id){
        StorageFile sf = findSF(id);
        if(sf == null){
            return Constants.SF_NOT_EXIST + "?| Error";
        }
        if(sf.getStatus() != null && sf.getStatus() == 1){
            return  "Khong the xoa vi SF Đã được thực hiện | Error";
        }
        entityManager.remove(sf);
        return Constants.DELETE_DATA_SUCCESS + "| Success";
    }

// --------------------------------------------------------------------------------------------
    public List<APIOSFInfo> listAPIOSFInfo(String filter, Integer status){
        String sql = "Select new " + APIOSFInfo.class.getName()
                + "(c.id, c.sfid, c.apiname,c.authentication ,c.request, "
                + " c.response, c.status) "
                + " from "+ APIOSF.class.getName()+ " c where "
                + " or lower(c.sfid) like :filter )"
                + " (lower(c.apiname) like :filter "
                + " and (c.status = :status or :status = -1) " +
                "order by c.id desc";
        Query q = entityManager.createQuery(sql);
        q.setParameter("filter", "%" + filter.toLowerCase() + "%");
        q.setParameter("status", status);
        return q.getResultList();
    }
    
    public void insertVoidAPIOSF(ApiInfo doInfo,Integer status,Long id){
        APIOSF sF = new APIOSF();
        if(doInfo == null){
            return ;
        }
        sF.setApiname(doInfo.getApiname());
        sF.setSfid(id);
        sF.setAuthentication(doInfo.getAuthentication());
        sF.setRequest(doInfo.getRequest());
        sF.setResponse(doInfo.getResponse());
        sF.setStatus(status);


        StorageFile flagAPI = findSFByfilenameAll(doInfo.getApiname());
        if(flagAPI != null){
            return ;
        }
        entityManager.persist(sF);
        entityManager.flush();
    }
    
    public void updateSF(Long id,Long sc, Long fail, Integer status){
        StorageFile updateAPI = null;
        if(id !=  null){
            updateAPI = findSF(id);
        }
        updateAPI.setSuccess(sc);
        updateAPI.setFail(fail);
        updateAPI.setStatus(status);
    }
        
//    public List<APIOSF> findAllAPIOSF() {
//        Query q = entityManager.
//                createNativeQuery("Select * from CMS_APIOSF  ", APIOSF.class);
//        List<APIOSF> list = q.getResultList();
//        if (list.isEmpty()) {
//            return null;
//        }
//        entityManager.flush();
//        return list;    
//    }
//            
    public List<APIOSFInfo> getDetailAPIOSF(Long sfid){
        Query q = entityManager.
                createNativeQuery("Select * from CMS_APIOSF  where lower(sfid) = lower(:sfid) ", APIOSF.class);
        q.setParameter("sfid", sfid);
        List<APIOSFInfo> list = q.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        list.get(0).setStatus(1);
        entityManager.flush();
        return list;
    }
//    -----

}
