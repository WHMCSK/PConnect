package cn.bgenius.pconnect.knw.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dm_knw_video_album")
public class TVideoAlbum {
    /**
     * 视频专辑
     */
    @Id
    private Integer id;

    @Column(name = "album_name")
    private String albumName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_timestamp")
    private Date lastTimestamp;

    /**
     * 获取视频专辑
     *
     * @return id - 视频专辑
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置视频专辑
     *
     * @param id 视频专辑
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return album_name
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * @param albumName
     */
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
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