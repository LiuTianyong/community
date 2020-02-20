package com.mmns.conmmunity.enums;

/**
 * @PackgeName: com.mmns.conmmunity.enums
 * @Author: LiuTianyong
 * Date: 2020/2/20 22:50
 * @Version:
 * @Description:
 */
public enum NotificationStatusEnum {
    UNREAD(0), READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}