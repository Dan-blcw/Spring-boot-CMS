/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author vnpt2
 */
@Setter
@Getter
@NoArgsConstructor

public class RolesInfo {

    private Long id;
    private String name;
    private String description;
    private String url;
    private Boolean assignGroup;
    private Long parentRoleId;
    private String parentRoleName;

    public RolesInfo(Long id, String name, String description, String url, Long parentRoleId, String parentRoleName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.parentRoleId = parentRoleId;
        this.parentRoleName = parentRoleName;
    }

}
