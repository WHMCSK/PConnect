package cn.bgenius.pconnect.todo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dm_usr_todoes_do_record")
public class TTodoesDoRecord {
    @Id
    private Integer id;

    private Integer todoesid;

    @Column(name = "pre_status")
    private String preStatus;

    @Column(name = "current_status")
    private String currentStatus;

    private String remark;

    @Column(name = "current_time")
    private Date currentTime;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_timestamp")
    private Date lastTimestamp;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return todoesid
     */
    public Integer getTodoesid() {
        return todoesid;
    }

    /**
     * @param todoesid
     */
    public void setTodoesid(Integer todoesid) {
        this.todoesid = todoesid;
    }

    /**
     * @return pre_status
     */
    public String getPreStatus() {
        return preStatus;
    }

    /**
     * @param preStatus
     */
    public void setPreStatus(String preStatus) {
        this.preStatus = preStatus;
    }

    /**
     * @return current_status
     */
    public String getCurrentStatus() {
        return currentStatus;
    }

    /**
     * @param currentStatus
     */
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return current_time
     */
    public Date getCurrentTime() {
        return currentTime;
    }

    /**
     * @param currentTime
     */
    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
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