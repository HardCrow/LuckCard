package com.huang.luck.service.ex;

public class CardNameException  extends ServiceException{
    public CardNameException() {
        super();
    }

    public CardNameException(String message) {
        super(message);
    }

    public CardNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardNameException(Throwable cause) {
        super(cause);
    }

    protected CardNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
