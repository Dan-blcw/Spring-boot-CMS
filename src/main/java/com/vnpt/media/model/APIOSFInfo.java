/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vnpt.media.model;

import java.util.Date;
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
public class APIOSFInfo {
    private Long id;
    private Long sfid;
    private String apiname;
    private String authentication;
    private String request;
    private String response;
    private Integer status;
    

    public APIOSFInfo(Long id,Long sfid, String apiname, String authentication, String request,String response,Integer status) {
        this.id = id;
        this.sfid = sfid;
        this.apiname = apiname;
        this.authentication = authentication;
        this.request = request;
        this.response = response;
        this.status = status;
    }
}
