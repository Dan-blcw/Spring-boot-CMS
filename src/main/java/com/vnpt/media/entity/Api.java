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

@Entity
@Table(name = "CMS_API")
@Data
public class Api implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "CMS_API_SEQ", sequenceName = "CMS_API_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CMS_API_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;

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

    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "STATUS")
    private Integer status;

    @Size(max = 200)
    @Column(name = "USERCREATE")
    private String usercreate;
}
