package com.ramostear.web.exception;

/**
 * @author ramostear
 * @create-time 2019/3/3 0003-3:18
 * @modify by :
 * @since:
 */
public class UserIdMismatchException extends RuntimeException {

    public UserIdMismatchException(){
        super();
    }

    public UserIdMismatchException(final String message,final Throwable cause){
        super(message, cause);
    }

    public UserIdMismatchException(final String message){
        super(message);
    }

    public UserIdMismatchException(final Throwable cause){
        super(cause);
    }

}
