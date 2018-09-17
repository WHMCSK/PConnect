package cn.bgenius.pconnect.todo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dm_usr_todoes_records_of_report_to")
public class TTodoesRecordsofReportto {
    /**
     * 这是todo的汇报记录表，汇报一个todo就多一条数据
     */
    @Id
    private Integer id;

    /**
     * 要汇报的todo的id
     */
    @Column(name = "todoes_id")
    private Integer todoesId;

    /**
     * 汇报的上级对象
     */
    @Column(name = "to_user_id")
    private Integer toUserId;

    /**
     * 汇报的内容采取json格式，将汇报当时完整的todo和todoes_do_record记录到这个字段
     */
    @Column(name = "report_content")
    private String reportContent;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_timestamp")
    private Date lastTimestamp;

    /**
     * 获取这是todo的汇报记录表，汇报一个todo就多一条数据
     *
     * @return id - 这是todo的汇报记录表，汇报一个todo就多一条数据
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置这是todo的汇报记录表，汇报一个todo就多一条数据
     *
     * @param id 这是todo的汇报记录表，汇报一个todo就多一条数据
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取要汇报的todo的id
     *
     * @return todoes_id - 要汇报的todo的id
     */
    public Integer getTodoesId() {
        return todoesId;
    }

    /**
     * 设置要汇报的todo的id
     *
     * @param todoesId 要汇报的todo的id
     */
    public void setTodoesId(Integer todoesId) {
        this.todoesId = todoesId;
    }

    /**
     * 获取汇报的上级对象
     *
     * @return to_user_id - 汇报的上级对象
     */
    public Integer getToUserId() {
        return toUserId;
    }

    /**
     * 设置汇报的上级对象
     *
     * @param toUserId 汇报的上级对象
     */
    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * 获取汇报的内容采取json格式，将汇报当时完整的todo和todoes_do_record记录到这个字段
     *
     * @return report_content - 汇报的内容采取json格式，将汇报当时完整的todo和todoes_do_record记录到这个字段
     */
    public String getReportContent() {
        return reportContent;
    }

    /**
     * 设置汇报的内容采取json格式，将汇报当时完整的todo和todoes_do_record记录到这个字段
     *
     * @param reportContent 汇报的内容采取json格式，将汇报当时完整的todo和todoes_do_record记录到这个字段
     */
    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
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