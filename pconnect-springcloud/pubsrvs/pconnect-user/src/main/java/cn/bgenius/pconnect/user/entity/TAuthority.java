package cn.bgenius.pconnect.user.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_authority")
@Data
public class TAuthority {
    @Id
    private Integer id;

    @Column(name = "authority_id")
    private Integer authorityId;

    @Column(name = "authority_type")
    private String authorityType;

    @Column(name = "resource_id")
    private Integer resourceId;

    @Column(name = "resource_type")
    private String resourceType;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}