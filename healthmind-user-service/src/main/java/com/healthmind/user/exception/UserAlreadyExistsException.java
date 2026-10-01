package com.healthmind.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserAlreadyExistsException extends RuntimeException{

    private final String email;
    private final HttpStatus status;
    private final String errorCode;

    public UserAlreadyExistsException(String email){
        super("User already exists with email: " + email);
        this.email = email;
        this.status = HttpStatus.CONFLICT;
        this.errorCode = "USER_ALREADY_EXISTS";
    }

}
