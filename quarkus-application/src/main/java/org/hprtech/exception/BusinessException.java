package org.hprtech.exception;

public class BusinessException extends Exception{

    private int status;
    public BusinessException() {
    }

    public BusinessException(int status,String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
