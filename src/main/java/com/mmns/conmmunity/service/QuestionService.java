package com.mmns.conmmunity.service;

import com.mmns.conmmunity.dto.PageinationDTO;
import com.mmns.conmmunity.dto.QuestionDTO;
import com.mmns.conmmunity.mapper.QuestionMapper;
import com.mmns.conmmunity.mapper.UserMapper;
import com.mmns.conmmunity.model.Question;
import com.mmns.conmmunity.model.QuestionExample;
import com.mmns.conmmunity.model.User;
import javafx.scene.layout.BorderWidths;
import org.apache.ibatis.session.RowBounds;
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

        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }


        if (page > totalPage) {
            page = totalPage;
        }


        if (page <= 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        if (page < 1) {
            page = 1;
        }
        pageinationDTO.setPageIntaion(totalPage, page);

        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(), new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
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

        Integer totalPage;

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());



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

        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(example, new RowBounds(offset, size));


        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageinationDTO.setQuestions(questionDTOList);
        return pageinationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        } else {
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());

            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(updateQuestion,example);
        }
    }
}
