package com.osk.team.domain;

import java.util.List;

public class Board {
    private int no;//게시판id
    private Member writer;//작성자. DB 컬럼명은 mno
    private int boardTypeNo;//게시판분류번호
    private int viewCount;//조회수
    private String title;//게시판 제목
    private String content;//게시판내용
    private String registeredDate;//작성일
    private List<Object> photos;//사진



    @Override
    public String toString() {
        return "Board [no=" + no + ", writer=" + writer + ", boardTypeNo=" + boardTypeNo + ", viewCount=" + viewCount
                + ", title=" + title + ", content=" + content + ", registeredDate=" + registeredDate + ", photos=" + photos
                + "]";
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public Member getWriter() {
        return writer;
    }
    public void setWriter(Member writer) {
        this.writer = writer;
    }
    public int getBoardTypeNo() {
        return boardTypeNo;
    }
    public void setBoardTypeNo(int boardTypeNo) {
        this.boardTypeNo = boardTypeNo;
    }
    public int getViewCount() {
        return viewCount;
    }
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
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

    public String getRegisteredDate() {
        return registeredDate;
    }
    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
    public List<Object> getPhotos() {
        return photos;
    }
    public void setPhotos(List<Object> photos) {
        this.photos = photos;
    }


}