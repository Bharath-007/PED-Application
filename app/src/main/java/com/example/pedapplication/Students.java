package com.example.pedapplication;

public class Students {
    public Students(String name, String registerNumber, String batch, String department,String year) {
        this.name = name;
        this.registerNumber = registerNumber;
        this.batch = batch;
        this.department = department;
        this.year = year;
    }

    public Students() {
    }

    String name,registerNumber,batch,department,year;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
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
}
