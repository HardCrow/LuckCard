package com.huang.luck.service.ex;

import javax.xml.ws.Service;

public class CardNumsException extends ServiceException {
    public CardNumsException() {
    }

    public CardNumsException(String message) {
        super(message);
    }

    public CardNumsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardNumsException(Throwable cause) {
        super(cause);
    }

    public CardNumsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
