package com.example.dreamhouse.Models;

public class Profile {

    String name, number,img,age,income,marr;

    public String getMarr() {
        return marr;
    }

    public void setMarr(String marr) {
        this.marr = marr;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public Profile(){

    }
    public Profile(String name, String number, String img, String age,String income,String marr){
        this.name=name;
        this.number=number;
        this.img=img;
        this.age=age;
        this.marr=marr;
        this.income=income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
