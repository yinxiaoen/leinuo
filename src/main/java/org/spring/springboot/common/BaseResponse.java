package org.spring.springboot.common;

import lombok.Data;

/**
 * Created by BookThief on 2016/6/12.
 */
@Data
public abstract class BaseResponse<T> {

    protected String code = "";
    protected String msg = "";
    protected boolean success;
    protected String traceID = "";
    protected T data;

}
