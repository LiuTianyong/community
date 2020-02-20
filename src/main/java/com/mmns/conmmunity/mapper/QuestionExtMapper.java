package com.mmns.conmmunity.mapper;

import com.mmns.conmmunity.model.Question;
import com.mmns.conmmunity.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionExtMapper {

    int incView(Question record);

    int incCommentCount(Question record);
}