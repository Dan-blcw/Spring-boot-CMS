/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vnpt.media.model;
import java.util.Date;
//import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author PHU MINH HUONG
 */

@Getter
@Setter
@NoArgsConstructor
public class ApiInfo {
    private Long id;
    private String apiname;
    private String authentication;
    private String request;
    private String response;
    private Date createTime;
    private Integer status;
    private String usercreate;
   
    public ApiInfo(Long id, String apiname, String authentication, String request,String response,Date createtime,Integer status,String usercreate) {
        this.id = id;
        this.apiname = apiname;
        this.authentication = authentication;
        this.request = request;
        this.response = response;
        this.createTime = createtime;
        this.status = status;
        this.usercreate = usercreate;
    }
}
