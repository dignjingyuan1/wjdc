package com.flow.snow.snow.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Questionnaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = true)
    private String nameAbc;
    @Column(nullable = true)
    private String age;
    @Column(nullable = true)
    private Date createDate;
    @Column(nullable = true)
    private String openid;
    @Column(nullable = true)
    private String content;
    @Column(nullable = true)
    private String type;
    @Column(nullable = true)
    private String sex;
}
