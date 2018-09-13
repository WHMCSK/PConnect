package cn.bgenius.pconnect.user.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user")
@Data
public class TUser {
    @Id
    @GeneratedValue(generator="JDBC")
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String birthday;

    private String address;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "tel_phone")
    private String telPhone;

    private String email;

    private String sex;


    private String status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}