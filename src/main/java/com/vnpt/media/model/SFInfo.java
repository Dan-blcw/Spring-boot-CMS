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
public class SFInfo {
    private Long id;
    private String filename;
    private Integer status;
    private Date createTime;
    private Long success;
    private Long fail;
    private String userinput;
    
    public SFInfo(Long id, String filename,Integer status,Date createTime, Long success, Long fail, String userinput) {
        this.id = id;
        this.filename = filename;
        this.status = status;
        this.createTime = createTime;
        this.success = success;
        this.fail = fail;
        this.userinput = userinput;
    }
    public SFInfo(String filename,Integer status,Date createTime, Long success, Long fail, String userinput) {
        this.filename = filename;
        this.status = status;
        this.createTime = createTime;
        this.success = success;
        this.fail = fail;
        this.userinput = userinput;
    }
    public SFInfo(String filename,Date createTime, String userinput) {
        this.filename = filename;
        this.createTime = createTime;
        this.userinput = userinput;
    }
}