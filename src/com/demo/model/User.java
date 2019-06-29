package com.demo.model;/**
  *ClassNmae:  User
  *Description:  TODO
  *@author  MasonWu
  *@date  2019/6/29 19:11
  *@version  1.0
  *Copyright (c) 2018-2020 Koreancoco All Rights Reserved.
  **/
public class User  {
    private String username;
    private String sex;
    private String age;
    private String phone;
    public User(String username, String sex, String age, String phone) {

        this.username = username;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{" +  "\"username\"" + ':' + "\""+ username +"\"" + ","+ "\"sex\""+':' +"\"" +sex +"\""+','+ "\"age\""+':' + "\"" + age + "\"" +','+"\"phone\""+':' + "\""+phone+"\"" + "}";
    }
}