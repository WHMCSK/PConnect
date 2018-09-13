package cn.bgenius.pconnect.todo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dm_knw_choice_question")
public class TChoiceQuestion {
    /**
     * （知识中心领域）选择题的题目
     */
    @Id
    private Integer id;

    private String title;

    /**
     * 学习卡片的内容
     */
    @Column(name = "study_card_content")
    private String studyCardContent;

    /**
     * 知识关联的URL
     */
    private String urls;

    private String tag;

    @Column(name = "audit_state")
    private String auditState;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_timestamp")
    private Date lastTimestamp;

    /**
     * 获取（知识中心领域）选择题的题目
     *
     * @return id - （知识中心领域）选择题的题目
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置（知识中心领域）选择题的题目
     *
     * @param id （知识中心领域）选择题的题目
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取学习卡片的内容
     *
     * @return study_card_content - 学习卡片的内容
     */
    public String getStudyCardContent() {
        return studyCardContent;
    }

    /**
     * 设置学习卡片的内容
     *
     * @param studyCardContent 学习卡片的内容
     */
    public void setStudyCardContent(String studyCardContent) {
        this.studyCardContent = studyCardContent;
    }

    /**
     * 获取知识关联的URL
     *
     * @return urls - 知识关联的URL
     */
    public String getUrls() {
        return urls;
    }

    /**
     * 设置知识关联的URL
     *
     * @param urls 知识关联的URL
     */
    public void setUrls(String urls) {
        this.urls = urls;
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
     * @return audit_state
     */
    public String getAuditState() {
        return auditState;
    }

    /**
     * @param auditState
     */
    public void setAuditState(String auditState) {
        this.auditState = auditState;
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