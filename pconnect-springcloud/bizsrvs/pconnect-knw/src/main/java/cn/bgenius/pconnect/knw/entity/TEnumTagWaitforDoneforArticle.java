package cn.bgenius.pconnect.knw.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dm_knw_enum_tag_wait_for_done_for_article")
public class TEnumTagWaitforDoneforArticle {
    /**
     * 用来存储枚举，标记等待处理文章的处理方式枚举
     */
    @Id
    private Integer id;

    @Column(name = "item_tag_wait_for_done")
    private String itemTagWaitForDone;

    private String desc;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_timestamp")
    private Date lastTimestamp;

    /**
     * 获取用来存储枚举，标记等待处理文章的处理方式枚举
     *
     * @return id - 用来存储枚举，标记等待处理文章的处理方式枚举
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用来存储枚举，标记等待处理文章的处理方式枚举
     *
     * @param id 用来存储枚举，标记等待处理文章的处理方式枚举
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return item_tag_wait_for_done
     */
    public String getItemTagWaitForDone() {
        return itemTagWaitForDone;
    }

    /**
     * @param itemTagWaitForDone
     */
    public void setItemTagWaitForDone(String itemTagWaitForDone) {
        this.itemTagWaitForDone = itemTagWaitForDone;
    }

    /**
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
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