package com.bear.bookhouse.pojo;

import java.util.Date;

/**
 * @author Spring-_-Bear
 * @datetime 2022/3/22 21:16
 */
public class Favorite {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private String title;
    private String author;
    private String coverPath;
    private Date collectTime;

    public Favorite() {
    }

    public Favorite(Integer id, Integer userId, Integer bookId, String title, String author, String coverPath, Date collectTime) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.coverPath = coverPath;
        this.collectTime = collectTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", coverPath='" + coverPath + '\'' +
                ", collectTime=" + collectTime +
                '}';
    }
}
