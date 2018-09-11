package cn.bgenius.pconnect.todo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dm_knw_article_collect")
public class TArticleCollect {
    @Id
    private Integer id;

    @Column(name = "article_title")
    private String articleTitle;

    @Column(name = "article_url")
    private String articleUrl;

    @Column(name = "article_brief_desc")
    private String articleBriefDesc;

    private String tag;

    /**
     * 等待去做的标签，例如“出口成章”、“熟记脉络”、“参照练习”、“反复训练”、“有待应证”、“应用到实际”、“加入文集”，“加入创作（整编到自己写的书籍或者系列文章）”、“加入PPT（自己把多方面知识和资料、想法整理成各个不同方面的PPT、以便今后工作、教学培训、售前售后使用）”，此字段值为枚举值。
     */
    @Column(name = "tag_wait_for_done")
    private String tagWaitForDone;

    private String author;

    /**
     * 文章字面编写时间，可能不是原创编写时间
     */
    @Column(name = "word_write_time")
    private Date wordWriteTime;

    /**
     * 原创编写时间
     */
    @Column(name = "origin_write_time")
    private Date originWriteTime;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_timestamp")
    private Date lastTimestamp;

    @Column(name = "article_content")
    private String articleContent;

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
     * @return article_title
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * @param articleTitle
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    /**
     * @return article_url
     */
    public String getArticleUrl() {
        return articleUrl;
    }

    /**
     * @param articleUrl
     */
    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    /**
     * @return article_brief_desc
     */
    public String getArticleBriefDesc() {
        return articleBriefDesc;
    }

    /**
     * @param articleBriefDesc
     */
    public void setArticleBriefDesc(String articleBriefDesc) {
        this.articleBriefDesc = articleBriefDesc;
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
     * 获取等待去做的标签，例如“出口成章”、“熟记脉络”、“参照练习”、“反复训练”、“有待应证”、“应用到实际”、“加入文集”，“加入创作（整编到自己写的书籍或者系列文章）”、“加入PPT（自己把多方面知识和资料、想法整理成各个不同方面的PPT、以便今后工作、教学培训、售前售后使用）”，此字段值为枚举值。
     *
     * @return tag_wait_for_done - 等待去做的标签，例如“出口成章”、“熟记脉络”、“参照练习”、“反复训练”、“有待应证”、“应用到实际”、“加入文集”，“加入创作（整编到自己写的书籍或者系列文章）”、“加入PPT（自己把多方面知识和资料、想法整理成各个不同方面的PPT、以便今后工作、教学培训、售前售后使用）”，此字段值为枚举值。
     */
    public String getTagWaitForDone() {
        return tagWaitForDone;
    }

    /**
     * 设置等待去做的标签，例如“出口成章”、“熟记脉络”、“参照练习”、“反复训练”、“有待应证”、“应用到实际”、“加入文集”，“加入创作（整编到自己写的书籍或者系列文章）”、“加入PPT（自己把多方面知识和资料、想法整理成各个不同方面的PPT、以便今后工作、教学培训、售前售后使用）”，此字段值为枚举值。
     *
     * @param tagWaitForDone 等待去做的标签，例如“出口成章”、“熟记脉络”、“参照练习”、“反复训练”、“有待应证”、“应用到实际”、“加入文集”，“加入创作（整编到自己写的书籍或者系列文章）”、“加入PPT（自己把多方面知识和资料、想法整理成各个不同方面的PPT、以便今后工作、教学培训、售前售后使用）”，此字段值为枚举值。
     */
    public void setTagWaitForDone(String tagWaitForDone) {
        this.tagWaitForDone = tagWaitForDone;
    }

    /**
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取文章字面编写时间，可能不是原创编写时间
     *
     * @return word_write_time - 文章字面编写时间，可能不是原创编写时间
     */
    public Date getWordWriteTime() {
        return wordWriteTime;
    }

    /**
     * 设置文章字面编写时间，可能不是原创编写时间
     *
     * @param wordWriteTime 文章字面编写时间，可能不是原创编写时间
     */
    public void setWordWriteTime(Date wordWriteTime) {
        this.wordWriteTime = wordWriteTime;
    }

    /**
     * 获取原创编写时间
     *
     * @return origin_write_time - 原创编写时间
     */
    public Date getOriginWriteTime() {
        return originWriteTime;
    }

    /**
     * 设置原创编写时间
     *
     * @param originWriteTime 原创编写时间
     */
    public void setOriginWriteTime(Date originWriteTime) {
        this.originWriteTime = originWriteTime;
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

    /**
     * @return article_content
     */
    public String getArticleContent() {
        return articleContent;
    }

    /**
     * @param articleContent
     */
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}