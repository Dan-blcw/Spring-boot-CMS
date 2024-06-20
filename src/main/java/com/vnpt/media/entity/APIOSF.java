/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vnpt.media.entity;

/**
 *
 * @author PHU MINH HUONG
 */

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "CMS_APIOSF")
@Data
public class APIOSF implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "CMS_APIOSF_SEQ", sequenceName = "CMS_APIOSF_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CMS_APIOSF_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "SFID")
    private Long sfid;
    
    @Size(max = 200)
    @Column(name = "APINAME")
    private String apiname;

    @Size(max = 200)
    @Column(name = "AUTHENTICATION")
    private String authentication;

    @Size(max = 200)
    @Column(name = "REQUEST")
    private String request;

    @Size(max = 200)
    @Column(name = "RESPONSE")
    private String response;

    @Column(name = "STATUS")
    private Integer status;
}