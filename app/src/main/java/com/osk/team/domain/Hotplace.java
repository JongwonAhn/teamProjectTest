package com.osk.team.domain;

import java.sql.Date;

public class Hotplace {

    private int no;
    private String title;
    private String content;
    private Date date;
    private int count;
    private String address;
    private String photo;



    @Override
    public String toString() {
        return "Hotplace [no=" + no +
                ", title=" + title +
                ", content=" + content +
                ", date=" + date +
                ", count=" + count +
                ", address=" + address +
                ", photo=" + photo + "]";
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}