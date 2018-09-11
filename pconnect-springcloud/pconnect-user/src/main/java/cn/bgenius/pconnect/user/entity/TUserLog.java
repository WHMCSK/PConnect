package cn.bgenius.pconnect.user.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_log")
@Data
public class TUserLog {
    @Id
    private Integer id;

    private String ip;

    /**
     * 用户名
     */
    private String username;

    /**
     * 操作时间
     */
    @Column(name = "opt_time")
    private Date optTime;

    /**
     * 用户sessionId
     */
    @Column(name = "session_id")
    private String sessionId;

    /**
     * 操作
     */
    private String action;

    /**
     * 访问的方法
     */
    private String method;

    /**
     * 数据库创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}