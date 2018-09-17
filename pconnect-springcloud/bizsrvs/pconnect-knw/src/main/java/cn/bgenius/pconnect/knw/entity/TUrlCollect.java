package cn.bgenius.pconnect.knw.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dm_knw_url_collect")
public class TUrlCollect {
    /**
     * 网站收藏
     */
    @Id
    private Integer id;

    private String url;

    @Column(name = "url_name")
    private String urlName;

    private String remark;

    private String tag;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_timestamp")
    private Date lastTimestamp;

    /**
     * 获取网站收藏
     *
     * @return id - 网站收藏
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置网站收藏
     *
     * @param id 网站收藏
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return url_name
     */
    public String getUrlName() {
        return urlName;
    }

    /**
     * @param urlName
     */
    public void setUrlName(String urlName) {
        this.urlName = urlName;
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
     * @return id_user
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * @param idUser
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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