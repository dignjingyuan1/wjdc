package com.flow.snow.snow.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String openid;
    @Column(nullable = false)
    private String province;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String hospitalName;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String jobName;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;
}
