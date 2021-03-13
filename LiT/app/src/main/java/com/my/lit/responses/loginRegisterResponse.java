package com.my.lit.responses;

public class loginRegisterResponse {
    String msg;
    int err;

    public loginRegisterResponse(String msg, int err) {
        this.msg = msg;
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public int getErr() {
        return err;
    }
}
