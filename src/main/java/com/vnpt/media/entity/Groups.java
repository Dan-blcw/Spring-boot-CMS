/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author vnpt2
 */
@Entity
@Table(name = "CMS_GROUPS")
@SequenceGenerator(name = "CMS_GROUPS_SEQ", sequenceName = "CMS_GROUPS_SEQ", allocationSize = 1)
@Getter
@Setter

public class Groups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CMS_GROUPS_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    
    @Size(max = 200)
    @Column(name = "NAME")
    private String name;
    
    @Size(max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "groups")
    private List<Users> listUsers;

    @JoinTable(name = "CMS_GROUP_ROLE", joinColumns = {
        @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Roles> listRoles;

    public Groups() {
    }

}
