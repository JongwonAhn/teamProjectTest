package com.osk.team.domain;

public class BoardPhoto {

  private String photo;//게시판 제목
  private int bno;//사진

  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public int getBno() {
    return bno;
  }
  public void setBno(int bno) {
    this.bno = bno;
  }

  @Override
  public String toString() {
    return "photo [photo=" + photo + ", bno=" + bno + "]";
  }



}
