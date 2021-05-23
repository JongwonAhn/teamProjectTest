package com.osk.team.domain;

public class Photo {
    private int no;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}