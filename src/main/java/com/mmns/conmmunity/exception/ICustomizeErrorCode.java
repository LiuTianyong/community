package com.mmns.conmmunity.exception;

/**
 * @PackgeName: com.mmns.conmmunity.exception
 * @Author: LiuTianyong
 * Date: 2020/2/20 14:44
 * @Version:
 * @Description:
 */
public interface ICustomizeErrorCode  {

    String message = null;

    String getMessage() ;
    Integer getCode();

}
