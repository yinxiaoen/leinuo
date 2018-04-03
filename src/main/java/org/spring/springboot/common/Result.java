package org.spring.springboot.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.spring.springboot.config.error.ErrorProperties;

@Data
public class Result<T> extends BaseResponse {

    public Result(String code, String msg, boolean success, String traceID, T data) {
        this.code = (code == null ? "001" : code);
        this.msg = (msg == null ? ("0".equals(code) ? "执行成功" : ErrorProperties.init().getErrorMessage(code)) : msg);
        this.success = success;
        this.traceID = (traceID == null ? StringUtils.EMPTY : traceID);
        this.data = (data == null ? new JSONObject() : data);
    }

    public Result(String code, String msg, String traceID, T data) {
        this(code, msg, "0".equals(code), traceID, data);
    }

    public Result(String code, String msg, boolean success, String traceID) {
        this(code, msg, success, traceID, null);
    }

    public Result(String code, String traceID, T data) {
        this(code, null, traceID, data);
    }

    public Result(String code, T data) {
        this(code, null, "0", data);
    }


    public Result() {
    }

    @Override
    public T getData() {
        return (T)data;
    }
}