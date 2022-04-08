package com.example.myapplication;

public class Object {
    private String objectName;
    private double lengtitude;
    private double plattitude;

    public Object(String name,double x, double y) {
        this.objectName = name;
        this.lengtitude = x;
        this.plattitude = y;
    }
    public Object(){
        this.objectName = "";
        this.lengtitude = 0;
        this.plattitude = 0;
    }

    public String getObjectName() {
        return objectName;
    }

    public double getLengtitude() {
        return lengtitude;
    }

    public double getPlattitude() {
        return plattitude;
    }
    public Object getObject()
    {
        return this;
    }
}
