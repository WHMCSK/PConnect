package cn.bgenius.pconnect.todo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dm_knw_compare")
public class TCompare {
    /**
     * 知识对比，这是一个基础的数据结构，所以用来承载所有对比文本数据
     */
    @Id
    private Integer id;

    /**
     * 对比主题id
     */
    @Column(name = "id_dm_knw_compare_title")
    private Integer idDmKnwCompareTitle;

    /**
     * 区别点
     */
    @Column(name = "difference_point")
    private String differencePoint;

    /**
     * 对比对象
     */
    @Column(name = "reference_obj")
    private String referenceObj;

    /**
     * 对比对象的现状
     */
    @Column(name = "current_state")
    private String currentState;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_timestamp")
    private Date lastTimestamp;

    /**
     * 获取知识对比，这是一个基础的数据结构，所以用来承载所有对比文本数据
     *
     * @return id - 知识对比，这是一个基础的数据结构，所以用来承载所有对比文本数据
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置知识对比，这是一个基础的数据结构，所以用来承载所有对比文本数据
     *
     * @param id 知识对比，这是一个基础的数据结构，所以用来承载所有对比文本数据
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取对比主题id
     *
     * @return id_dm_knw_compare_title - 对比主题id
     */
    public Integer getIdDmKnwCompareTitle() {
        return idDmKnwCompareTitle;
    }

    /**
     * 设置对比主题id
     *
     * @param idDmKnwCompareTitle 对比主题id
     */
    public void setIdDmKnwCompareTitle(Integer idDmKnwCompareTitle) {
        this.idDmKnwCompareTitle = idDmKnwCompareTitle;
    }

    /**
     * 获取区别点
     *
     * @return difference_point - 区别点
     */
    public String getDifferencePoint() {
        return differencePoint;
    }

    /**
     * 设置区别点
     *
     * @param differencePoint 区别点
     */
    public void setDifferencePoint(String differencePoint) {
        this.differencePoint = differencePoint;
    }

    /**
     * 获取对比对象
     *
     * @return reference_obj - 对比对象
     */
    public String getReferenceObj() {
        return referenceObj;
    }

    /**
     * 设置对比对象
     *
     * @param referenceObj 对比对象
     */
    public void setReferenceObj(String referenceObj) {
        this.referenceObj = referenceObj;
    }

    /**
     * 获取对比对象的现状
     *
     * @return current_state - 对比对象的现状
     */
    public String getCurrentState() {
        return currentState;
    }

    /**
     * 设置对比对象的现状
     *
     * @param currentState 对比对象的现状
     */
    public void setCurrentState(String currentState) {
        this.currentState = currentState;
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