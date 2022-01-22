package com.son.students;

public class Student {
    private String ID;
    private String Name;
    private String Major;

    public Student(String id, String name, String major) {
        this.ID = id;
        this.Name = name;
        this.Major = major;
    }
    public String getID(){
        return this.ID;
    }
    public String getName(){
        return this.Name;
    }
    public String getMajor(){
        return this.Major;
    }
    public void setID(String id){
        this.ID = id;
    }
    public void setName(String name){
        this.Name = name;
    }
    public void setMajor(String major){
        this.Major = major;
    }
    public String toString(){
        return this.ID + " - " + this.Name;
    }
}
