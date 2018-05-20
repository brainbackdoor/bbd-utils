package com.brainbackdoor.demo.exception;

public class BaseException extends RuntimeException{
    public BaseException(String message) { super(message); }
    public BaseException(Throwable t, String message) { super(message, t); }

}
