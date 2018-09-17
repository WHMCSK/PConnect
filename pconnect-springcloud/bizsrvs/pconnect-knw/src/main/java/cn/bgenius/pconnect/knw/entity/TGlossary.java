package cn.bgenius.pconnect.knw.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "glossary")
public class TGlossary {
    @Id
    private Integer id;

    private String term;

    @Column(name = "`desc`")
    private String desc;

    private String category;

    @Column(name = "id_it_system")
    private Integer idItSystem;

    @Column(name = "id_prj_project")
    private Integer idPrjProject;

    private String urls;

    /**
     * 是否过时（0：为过时，1:过时的）
     */
    @Column(name = "is_deprecate")
    private Integer isDeprecate;

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
     * @return term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term
     */
    public void setTerm(String term) {
        this.term = term;
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
     * @return id_it_system
     */
    public Integer getIdItSystem() {
        return idItSystem;
    }

    /**
     * @param idItSystem
     */
    public void setIdItSystem(Integer idItSystem) {
        this.idItSystem = idItSystem;
    }

    /**
     * @return id_prj_project
     */
    public Integer getIdPrjProject() {
        return idPrjProject;
    }

    /**
     * @param idPrjProject
     */
    public void setIdPrjProject(Integer idPrjProject) {
        this.idPrjProject = idPrjProject;
    }

    /**
     * @return urls
     */
    public String getUrls() {
        return urls;
    }

    /**
     * @param urls
     */
    public void setUrls(String urls) {
        this.urls = urls;
    }

    /**
     * 获取是否过时（0：为过时，1:过时的）
     *
     * @return is_deprecate - 是否过时（0：为过时，1:过时的）
     */
    public Integer getIsDeprecate() {
        return isDeprecate;
    }

    /**
     * 设置是否过时（0：为过时，1:过时的）
     *
     * @param isDeprecate 是否过时（0：为过时，1:过时的）
     */
    public void setIsDeprecate(Integer isDeprecate) {
        this.isDeprecate = isDeprecate;
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