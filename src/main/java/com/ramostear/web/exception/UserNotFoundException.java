package com.ramostear.web.exception;
/**
 * @author ramostear
 * @create-time 2019/3/3 0003-3:15
 * @modify by :
 * @since:
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(final String message,final Throwable cause){
        super(message, cause);
    }

    public UserNotFoundException(final String message){
        super(message);
    }

    public UserNotFoundException(final Throwable cause){
        super(cause);
    }
}
