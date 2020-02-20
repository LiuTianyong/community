package com.mmns.conmmunity.mapper;

import com.mmns.conmmunity.model.Comment;
import org.springframework.stereotype.Component;

@Component
public interface CommentExtMapper {
    int incCommentCount(Comment comment);

}