package com.osk.team.domain;

import java.sql.Date;

public class Member {
    private int no;//멤버번호
    private String name;//이름
    private String password;//암호
    private String email;//이메일
    private String photo;//사진
    private Date birth;//생년월일

    private int tel;//전화번호w
    private int gender;//성별
    private int status;//탈퇴여부
    private int power;//관리자권한
    private int count;//제제횟수


    @Override
    public String toString() {
        return "Member [no=" + no + ", name=" + name + ", password=" + password + ", email=" + email
                + ", photo=" + photo + ", birth=" + birth + ", tel=" + tel + ", gender=" + gender
                + ", status=" + status + ", power=" + power + ", count=" + count + "]";
    }


    public int getNo() {
        return no;
    }


    public void setNo(int no) {
        this.no = no;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhoto() {
        return photo;
    }


    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public Date getBirth() {
        return birth;
    }


    public void setBirth(Date birth) {
        this.birth = birth;
    }


    public int getTel() {
        return tel;
    }


    public void setTel(int tel) {
        this.tel = tel;
    }


    public int getGender() {
        return gender;
    }


    public void setGender(int gender) {
        this.gender = gender;
    }


    public int getStatus() {
        return status;
    }


    public void setStatus(int status) {
        this.status = status;
    }


    public int getPower() {
        return power;
    }


    public void setPower(int power) {
        this.power = power;
    }


    public int getCount() {
        return count;
    }


    public void setCount(int count) {
        this.count = count;
    }


}