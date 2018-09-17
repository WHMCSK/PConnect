package cn.bgenius.pconnect.knw.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dm_knw_category")
public class TCategory {
    /**
     * 知识分类
     */
    @Id
    private Integer id;

    private String category;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_timestamp")
    private Date lastTimestamp;

    /**
     * 获取知识分类
     *
     * @return id - 知识分类
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置知识分类
     *
     * @param id 知识分类
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
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