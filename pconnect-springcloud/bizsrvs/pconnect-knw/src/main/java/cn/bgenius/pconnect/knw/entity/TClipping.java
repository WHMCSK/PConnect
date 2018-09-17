package cn.bgenius.pconnect.knw.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dm_knw_clipping")
public class TClipping {
    /**
     * 剪报
     */
    @Id
    private Integer id;

    @Column(name = "id_user")
    private String idUser;

    private String content;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_timestamp")
    private Date lastTimestamp;

    /**
     * 获取剪报
     *
     * @return id - 剪报
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置剪报
     *
     * @param id 剪报
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return id_user
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * @param idUser
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return last_timestamp
     */
    public Date getLastTimestamp() {
        return lastTimestamp;
    }

    /**
     * @param lastTimestamp
     */
    public void setLastTimestamp(Date lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }
}