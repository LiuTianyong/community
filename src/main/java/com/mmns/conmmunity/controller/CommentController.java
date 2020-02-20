package com.mmns.conmmunity.controller;

import com.mmns.conmmunity.dto.CommentCreateDTO;
import com.mmns.conmmunity.dto.CommentDTO;
import com.mmns.conmmunity.dto.ResultDTO;
import com.mmns.conmmunity.enums.CommentTypeEnum;
import com.mmns.conmmunity.exception.CustomizeErrorCode;
import com.mmns.conmmunity.model.Comment;
import com.mmns.conmmunity.model.User;
import com.mmns.conmmunity.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @PackgeName: com.mmns.conmmunity.controller
 * @Author: LiuTianyong
 * Date: 2020/2/20 18:10
 * @Version:
 * @Description:
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
// || StringUtils.isBlank(commentCreateDTO.getContent())
        if (commentCreateDTO == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment, user);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
