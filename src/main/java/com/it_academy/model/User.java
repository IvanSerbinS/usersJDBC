package com.it_academy.model;

import java.util.InputMismatchException;

public class User {
    private int userid;
    private String name;
    private String address;

    public User() {
    }

    public int getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InputMismatchException {
        if (name.length() == 0) {
            throw new InputMismatchException ("Name required.");
        }else if (name.length() > 50) {
            throw new InputMismatchException ("Name mustn't be longer than 50 characters.");
        }else{
            this.name = name;
        }
    }

    public String getAddress() throws InputMismatchException{

        return address;
    }

    public void setAddress(String address) {
        if (address.length() > 255) {
            throw new InputMismatchException("Address mustn't be longer than 255 characters.");
        }else{
            this.address = address;
        }
    }
}
