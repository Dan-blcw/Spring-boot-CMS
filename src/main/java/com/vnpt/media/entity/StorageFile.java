/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vnpt.media.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author PHU MINH HUONG
 */

@Entity
@Table(name = "CMS_SF")
@Data
public class StorageFile implements Serializable{
        
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "CMS_SF_SEQ", sequenceName = "CMS_SF_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CMS_SF_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;

    @Size(max = 200)
    @Column(name = "FILENAME")
    private String filename;

    @Column(name = "STATUS")
    private Integer status;
    
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Column(name = "SUCCESS")
    private Long success;
    
    @Column(name = "FAIL")
    private Long fail;

    @Size(max = 200)
    @Column(name = "USERINPUT")
    private String userinput;
}
