package com.zn.bean;
 public class TestService {
     private String name;
     public String getName() {
        return name;
     }
     public void setName(String name) {
        this.name = name;
     }
     public void print(){
        System.out.println("动态载入bean,name="+name);
     }
 }
