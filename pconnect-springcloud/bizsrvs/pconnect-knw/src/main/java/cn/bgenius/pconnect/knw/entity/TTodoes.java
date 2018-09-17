package cn.bgenius.pconnect.knw.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dm_usr_todoes")
public class TTodoes {
    @Id
    private Integer id;

    private String todo;

    /**
     * todo实际执行的时间
     */
    @Column(name = "todo_date")
    private Date todoDate;

    private String status;

    private Integer priority;

    /**
     * 历时限制，主要是区分长期和当日任务，这里设置标准（长期，冲刺期间，当日）
     */
    @Column(name = "period_limit")
    private String periodLimit;

    private String tag;

    @Column(name = "owner_id")
    private Integer ownerId;

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
     * @return todo
     */
    public String getTodo() {
        return todo;
    }

    /**
     * @param todo
     */
    public void setTodo(String todo) {
        this.todo = todo;
    }

    /**
     * 获取todo实际执行的时间
     *
     * @return todo_date - todo实际执行的时间
     */
    public Date getTodoDate() {
        return todoDate;
    }

    /**
     * 设置todo实际执行的时间
     *
     * @param todoDate todo实际执行的时间
     */
    public void setTodoDate(Date todoDate) {
        this.todoDate = todoDate;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * @param priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 获取历时限制，主要是区分长期和当日任务，这里设置标准（长期，冲刺期间，当日）
     *
     * @return period_limit - 历时限制，主要是区分长期和当日任务，这里设置标准（长期，冲刺期间，当日）
     */
    public String getPeriodLimit() {
        return periodLimit;
    }

    /**
     * 设置历时限制，主要是区分长期和当日任务，这里设置标准（长期，冲刺期间，当日）
     *
     * @param periodLimit 历时限制，主要是区分长期和当日任务，这里设置标准（长期，冲刺期间，当日）
     */
    public void setPeriodLimit(String periodLimit) {
        this.periodLimit = periodLimit;
    }

    /**
     * @return tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return owner_id
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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