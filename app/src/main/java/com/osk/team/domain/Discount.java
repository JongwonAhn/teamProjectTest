package com.osk.team.domain;

import java.sql.Date;
import java.util.List;

public class Discount {
    private int no;//게시판 번호
    private String title;//게시판 제목
    private String content;//게시판 내용
    private Date registeredDate;//작성일
    private int viewCount;//조회수
    private String photo;//사진


    @Override
    public String toString() {
        return "Discount{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", registeredDate=" + registeredDate +
                ", viewCount=" + viewCount +
                ", photo='" + photo + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
