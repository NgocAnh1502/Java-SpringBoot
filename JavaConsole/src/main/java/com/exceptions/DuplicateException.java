package com.exceptions;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String mess){
        super(mess);
    }
}
