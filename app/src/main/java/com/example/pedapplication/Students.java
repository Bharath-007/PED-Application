package com.example.pedapplication;

public class Students {
    public Students(String name, String registerNumber, String batch, String department) {
        this.name = name;
        this.registerNumber = registerNumber;
        this.batch = batch;
        this.department = department;
    }

    public Students() {
    }

    String name,registerNumber,batch,department;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
