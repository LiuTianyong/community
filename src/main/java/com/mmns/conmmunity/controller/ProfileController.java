package com.mmns.conmmunity.controller;

import com.mmns.conmmunity.dto.PageinationDTO;
import com.mmns.conmmunity.mapper.UserMapper;
import com.mmns.conmmunity.model.User;
import com.mmns.conmmunity.service.QuestionService;
import javafx.scene.control.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.awt.SunHints;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {


    @Autowired
    private QuestionService questionService;

    @RequestMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable String action,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {


        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        PageinationDTO pageinationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination", pageinationDTO);

        return "profile";
    }
}
