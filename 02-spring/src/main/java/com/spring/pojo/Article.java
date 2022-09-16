package com.spring.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Article {
    @ApiModelProperty(value="id",example = "1")
    private Long id;
    @ApiModelProperty(value="title",example = "title-A")
    private String title;
    @ApiModelProperty(value="type",example = "1")
    private String type;
    @ApiModelProperty(value="status",example = "1")
    private String status;
    @ApiModelProperty(value="content",example = "Content-A")
    private String content;
    @ApiModelProperty(value="userId",example = "1")
    private Long userId;
    private Date createdTime;
    private Date modifiedTime;
    private List<Tag> tags;
    @ApiModelProperty(value="tagIds",example = "[1,2,3]")
    private Integer[] tagIds;
    private User author;

    public void setTagIds(Integer[] tagIds) {
        this.tagIds = tagIds;
    }

    public Integer[] getTagIds() {
        return tagIds;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {//alt+insert
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", author=" + author +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", tags=" + tags +
                ", tagIds=" + Arrays.toString(tagIds) +
                '}';
    }
}
