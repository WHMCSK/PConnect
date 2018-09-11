package cn.bgenius.pconnect.todo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dm_knw_choice_answer")
public class TChoiceAnswer {
    /**
     * (知识中心领域)选择题的答案可选项
     */
    @Id
    private Integer id;

    @Column(name = "id_choice_question")
    private String idChoiceQuestion;

    @Column(name = "one_answer")
    private String oneAnswer;

    @Column(name = "is_right_answer")
    private Boolean isRightAnswer;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_timestamp")
    private Date lastTimestamp;

    /**
     * 获取(知识中心领域)选择题的答案可选项
     *
     * @return id - (知识中心领域)选择题的答案可选项
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置(知识中心领域)选择题的答案可选项
     *
     * @param id (知识中心领域)选择题的答案可选项
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return id_choice_question
     */
    public String getIdChoiceQuestion() {
        return idChoiceQuestion;
    }

    /**
     * @param idChoiceQuestion
     */
    public void setIdChoiceQuestion(String idChoiceQuestion) {
        this.idChoiceQuestion = idChoiceQuestion;
    }

    /**
     * @return one_answer
     */
    public String getOneAnswer() {
        return oneAnswer;
    }

    /**
     * @param oneAnswer
     */
    public void setOneAnswer(String oneAnswer) {
        this.oneAnswer = oneAnswer;
    }

    /**
     * @return is_right_answer
     */
    public Boolean getIsRightAnswer() {
        return isRightAnswer;
    }

    /**
     * @param isRightAnswer
     */
    public void setIsRightAnswer(Boolean isRightAnswer) {
        this.isRightAnswer = isRightAnswer;
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