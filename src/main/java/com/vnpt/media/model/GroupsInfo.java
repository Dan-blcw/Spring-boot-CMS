/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.model;

import java.util.Date;
import java.util.List;
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
public class GroupsInfo {

    private Long id;
    private String name;
    private String description;
    private Date createTime;
    private Date updateTime;
    private List<RolesInfo> listRoles;
    private List<UsersInfo> listUsers;

    public GroupsInfo(Long id, String name, String description, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public GroupsInfo(Long id, String name, String description, Date createTime, Date updateTime, List<RolesInfo> listRoles, List<UsersInfo> listUsers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.listRoles = listRoles;
        this.listUsers = listUsers;
    }
}
