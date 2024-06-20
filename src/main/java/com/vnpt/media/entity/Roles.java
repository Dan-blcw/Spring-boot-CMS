/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author vnpt2
 */
@Entity
@Table(name = "CMS_ROLES")
@Data

public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
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
    @Size(max = 200)
    @Column(name = "URL")
    private String url;
    @ManyToMany(mappedBy = "listRoles")
    private List<Groups> listGroups;

    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Roles parentRoles;

    @OneToMany(mappedBy = "parentRoles")
    private List<Roles> listRoles;

    @Transient
    private Boolean assignGroup;

}
