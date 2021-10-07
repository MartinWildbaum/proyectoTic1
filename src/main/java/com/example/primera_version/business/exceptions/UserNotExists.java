package com.example.primera_version.business.exceptions;

public class UserNotExists extends Exception{
    public UserNotExists(String message) {
        super(message);
    }
}
