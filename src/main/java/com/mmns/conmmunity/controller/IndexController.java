package com.mmns.conmmunity.controller;


import com.mmns.conmmunity.dto.PageinationDTO;
import com.mmns.conmmunity.dto.QuestionDTO;
import com.mmns.conmmunity.mapper.QuestionMapper;
import com.mmns.conmmunity.mapper.UserMapper;
import com.mmns.conmmunity.model.Question;
import com.mmns.conmmunity.model.User;
import com.mmns.conmmunity.service.QuestionService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {



        PageinationDTO pageination = questionService.list(page,size);
        model.addAttribute("pageination", pageination);

        return "index";
    }

}