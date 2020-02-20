package com.mmns.conmmunity.dto;

import lombok.Data;

/**
 * @PackgeName: com.mmns.conmmunity.dto
 * @Author: LiuTianyong
 * Date: 2020/2/20 22:58
 * @Version:
 * @Description:
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}