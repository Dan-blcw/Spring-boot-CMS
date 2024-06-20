/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author vnpt2
 */
@Getter
@Setter
@NoArgsConstructor
public class UsersInfo {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private Long groupId;
    private String groupName;

    public UsersInfo(Long id, String username, String password, String email, String phone, Integer status, Date createTime,
            Date updateTime, Long groupId, String groupName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.groupId = groupId;
        this.groupName = groupName;
    }

}
