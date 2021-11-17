package com.example.pedapplication;

public class Students {


    public Students() {
    }

    String name,rollno,batch,department,year,gender,status;

    public Students(String name, String rollno, String batch, String department, String year, String gender) {
        this.name = name;
        this.rollno = rollno;
        this.batch = batch;
        this.department = department;
        this.year = year;
        this.gender = gender;
    }

    public Students(String name, String rollno, String batch, String department, String year, String gender, String status) {
        this.name = name;
        this.rollno = rollno;
        this.batch = batch;
        this.department = department;
        this.year = year;
        this.gender = gender;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
