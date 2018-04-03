package org.spring.springboot.config.error;

import org.spring.springboot.utils.CommonUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class ErrorProperties {

    private static final String ERROR_MESSAGE_PATH = "classpath:errorMessage";
    private static ErrorProperties errorProperties = new ErrorProperties();
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

    private ErrorProperties() {
        messageSource.setBasename(ERROR_MESSAGE_PATH);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
    }
    public static ErrorProperties init() {
        return errorProperties;
    }
    public String getErrorMessage(String errorCode) {
        String errorMessage = messageSource.getMessage(errorCode, null, null);
        if (CommonUtils.notNull(errorMessage)) {
            return errorMessage;
        }
        return "";
    }
}
