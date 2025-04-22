package com.personalProject.shoply.data.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private static int count;

    public Customer(){
        count++;
    }
    public Customer(String fullName,String phoneNumber){
        count++;
        this.id=(long)count;
        this.fullName=fullName;
        this.phoneNumber=phoneNumber;

    }
    public Customer(Long id, String fullName,String phoneNumber){
        count++;
        this.id=id;
        this.fullName=fullName;
        this.phoneNumber=phoneNumber;

    }

}
