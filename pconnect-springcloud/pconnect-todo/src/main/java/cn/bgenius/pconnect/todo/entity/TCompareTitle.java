package cn.bgenius.pconnect.todo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dm_knw_compare_title")
public class TCompareTitle {
    /**
     * 对比的主题
     */
    @Id
    private Integer id;

    private String title;

    /**
     * 区分的标记，比如对比的轮次，对比的数据来源等等
     */
    @Column(name = "division_tag")
    private String divisionTag;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_timestamp")
    private Date lastTimestamp;

    /**
     * 获取对比的主题
     *
     * @return id - 对比的主题
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置对比的主题
     *
     * @param id 对比的主题
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
     * 获取区分的标记，比如对比的轮次，对比的数据来源等等
     *
     * @return division_tag - 区分的标记，比如对比的轮次，对比的数据来源等等
     */
    public String getDivisionTag() {
        return divisionTag;
    }

    /**
     * 设置区分的标记，比如对比的轮次，对比的数据来源等等
     *
     * @param divisionTag 区分的标记，比如对比的轮次，对比的数据来源等等
     */
    public void setDivisionTag(String divisionTag) {
        this.divisionTag = divisionTag;
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