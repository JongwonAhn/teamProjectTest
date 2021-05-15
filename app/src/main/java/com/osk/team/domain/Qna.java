package com.osk.team.domain;

import java.sql.Date;

public class Qna {
  private int no; // QnA번호
  private Member writer; // 작성자
  private Date registeredDate; //작성일
  private String title; //제목
  private String content; //내용
  private String answer; //답변내용
  private Date answerDate; //답변일

  @Override
  public String toString() {
    return "Qna [no=" + no + ", writer=" + writer + ", registeredDate=" + registeredDate
        + ", title=" + title + ", content=" + content + ", answer=" + answer + ", answerDate="
        + answerDate + "]";
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
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
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
  public String getAnswer() {
    return answer;
  }
  public void setAnswer(String answer) {
    this.answer = answer;
  }
  public Date getAnswerDate() {
    return answerDate;
  }
  public void setAnswerDate(Date answerDate) {
    this.answerDate = answerDate;
  }



}