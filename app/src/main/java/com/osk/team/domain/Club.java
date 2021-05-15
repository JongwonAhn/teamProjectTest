package com.osk.team.domain;

import java.sql.Date;
import java.util.List;

public class Club {
    /*
    1.맴버번호 배열로 가져와야되는거 아닌지?
    2.현재인원도 출력되게!(방장은 이미들어왔으니 현재인원에 1 포함하고 다른 사람이 클럽참여하면 +1되게!)
    3.사진-멤버 저장/불러오기 구현하기
    4.클럽 상세보기에서 사진이 이상하게 출력
    5.클럽생성시에 사진 넣으면 NPE뜸
     */

    private int no;//클럽번호
    private int memberNo;//멤버번호--------------여기 배열로 가져와야되는거 아닌지?

    private Member writer;//작성자
    private List<Member> members;//현재 인원

    private String arrive;//도착지
    private String theme;//테마
    private String title;//제목
    private String content;//내용
    private Date startDate;//가는날
    private Date endDate;//오는날
    private int total;//인원수
    private String photo1;//사진
    private String photo2;//사진
    private String photo3;//사진

    @Override
    public String toString() {
        return "Club{" +
                "no=" + no +
                ", memberNo=" + memberNo +
                ", writer=" + writer +
                ", members=" + members +
                ", arrive='" + arrive + '\'' +
                ", theme='" + theme + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", total=" + total +
                ", photo1='" + photo1 + '\'' +
                ", photo2='" + photo2 + '\'' +
                ", photo3='" + photo3 + '\'' +
                '}';
    }

    public Member getWriter() {
        return writer;
    }

    public void setWriter(Member writer) {
        this.writer = writer;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
