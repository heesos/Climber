package com.diploma.climber.domain;

public class IdHolder {
    private int id;
    public int getData() {return id;}
    public void setId(int id) {this.id = id;}

    private static final IdHolder holder = new IdHolder();
    public static IdHolder getInstance() {return holder;}
}
