package com.mmns.conmmunity.service;

import com.mmns.conmmunity.dto.PageinationDTO;
import com.mmns.conmmunity.dto.QuestionDTO;
import com.mmns.conmmunity.mapper.QuestionMapper;
import com.mmns.conmmunity.mapper.UserMapper;
import com.mmns.conmmunity.model.Question;
import com.mmns.conmmunity.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;



    public PageinationDTO list(Integer page, Integer size) {
        PageinationDTO pageinationDTO = new PageinationDTO();


        Integer totalPage;

        Integer totalCount = questionMapper.count();

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }

        if (page > totalPage) {
            page = totalPage;
        }


        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        pageinationDTO.setPageIntaion(totalPage, page);

        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findId(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageinationDTO.setQuestions(questionDTOList);


        return pageinationDTO;
    }

    public PageinationDTO list(Integer userId, Integer page, Integer size) {
        PageinationDTO pageinationDTO = new PageinationDTO();
        Integer totalCount = questionMapper.countByUserId(userId);

        Integer totalPage;

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }

        if (page > totalPage) {
            page = totalPage;
        }

        pageinationDTO.setPageIntaion(totalPage, page);

        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.listByUserId(userId,offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findId(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageinationDTO.setQuestions(questionDTOList);
        return pageinationDTO;
    }
}
