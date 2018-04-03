package org.spring.springboot.exception;

import org.apache.commons.lang3.StringUtils;
import org.spring.springboot.config.error.ErrorProperties;
import org.spring.springboot.config.error.SupplyErrorCode;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String code = StringUtils.EMPTY;
    private String msg = StringUtils.EMPTY;

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String code) {
        this(code, ErrorProperties.init().getErrorMessage(code));
    }

    public BusinessException(SupplyErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
