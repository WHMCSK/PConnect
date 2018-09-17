package cn.bgenius.pconnect.knw.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dm_knw_teleplay")
public class TTeleplay {
    /**
     * 影视领域，电视剧
     */
    @Id
    private Integer id;

    @Column(name = "teleplay_name")
    private String teleplayName;

    private String tag;

    /**
     * 未看、未看完、正在看、已看完
     */
    @Column(name = "watch_state")
    private String watchState;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_timestamp")
    private Date lastTimestamp;

    /**
     * 获取影视领域，电视剧
     *
     * @return id - 影视领域，电视剧
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置影视领域，电视剧
     *
     * @param id 影视领域，电视剧
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return teleplay_name
     */
    public String getTeleplayName() {
        return teleplayName;
    }

    /**
     * @param teleplayName
     */
    public void setTeleplayName(String teleplayName) {
        this.teleplayName = teleplayName;
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
     * 获取未看、未看完、正在看、已看完
     *
     * @return watch_state - 未看、未看完、正在看、已看完
     */
    public String getWatchState() {
        return watchState;
    }

    /**
     * 设置未看、未看完、正在看、已看完
     *
     * @param watchState 未看、未看完、正在看、已看完
     */
    public void setWatchState(String watchState) {
        this.watchState = watchState;
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