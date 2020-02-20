package com.mmns.conmmunity.exception;

/**
 * @PackgeName: com.mmns.conmmunity.exception
 * @Author: LiuTianyong
 * Date: 2020/2/20 14:26
 * @Version:
 * @Description:
 */
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}