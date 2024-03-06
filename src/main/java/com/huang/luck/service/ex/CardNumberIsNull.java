package com.huang.luck.service.ex;

public class CardNumberIsNull extends ServiceException{
    public CardNumberIsNull() {
        super();
    }

    public CardNumberIsNull(String message) {
        super(message);
    }

    public CardNumberIsNull(String message, Throwable cause) {
        super(message, cause);
    }

    public CardNumberIsNull(Throwable cause) {
        super(cause);
    }

    protected CardNumberIsNull(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
