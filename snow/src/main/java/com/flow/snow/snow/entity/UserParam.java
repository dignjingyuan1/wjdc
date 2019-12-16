package com.flow.snow.snow.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String openid;
    private String province;
    private String hospitalName;
    private String city;
    private String title;
    private String jobName;
    private String validate;
}
