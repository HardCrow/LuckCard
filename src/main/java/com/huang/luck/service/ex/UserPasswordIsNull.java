package com.huang.luck.service.ex;

public class UserPasswordIsNull extends ServiceException {
    //鼠标右键  generate  override  可以自动生成下面的方法
    public UserPasswordIsNull() {
        super();
    }

    public UserPasswordIsNull(String message) {
        super(message);
    }

    public UserPasswordIsNull(String message, Throwable cause) {
        super(message, cause);
    }

    public UserPasswordIsNull(Throwable cause) {
        super(cause);
    }

    protected UserPasswordIsNull(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
