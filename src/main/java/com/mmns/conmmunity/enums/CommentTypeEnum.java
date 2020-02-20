package com.mmns.conmmunity.enums;

/**
 * @PackgeName: com.mmns.conmmunity.enums
 * @Author: LiuTianyong
 * Date: 2020/2/20 21:13
 * @Version:
 * @Description:
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;


    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getType() == type) {
                return true;
            }
        }
        return false;
    }
}