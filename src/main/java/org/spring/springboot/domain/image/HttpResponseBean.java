package org.spring.springboot.domain.image;

/**
 * Created by lenovo on 2017/5/7.
 */
public class HttpResponseBean {
        private String code;
        private String success;
        private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
