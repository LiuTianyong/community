package com.mmns.conmmunity.dto;

import com.mmns.conmmunity.model.User;
import lombok.Data;

/**
 * @PackgeName: com.mmns.conmmunity.dto
 * @Author: LiuTianyong
 * Date: 2020/2/20 18:18
 * @Version:
 * @Description:
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private Integer commentCount;
    private String content;
    private User user;
}