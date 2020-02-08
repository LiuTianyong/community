package com.mmns.conmmunity.controller;

import com.mmns.conmmunity.dto.AccessTokenDTO;
import com.mmns.conmmunity.dto.GithupUser;
import com.mmns.conmmunity.mapper.UserMapper;
import com.mmns.conmmunity.model.User;
import com.mmns.conmmunity.provider.GithupProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithupProvider gitHupProvider;

    // 取配置文件内key对应的value值
    @Value("${githup.client.id}")
    private String clientId;
    @Value("${githup.client.secret}")
    private String clientSecret;
    @Value("${githup.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) throws IOException {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();

//        System.out.println(clientId);
//        System.out.println(clientSecret);
//        System.out.println(redirectUri);

        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);

        String accessToken = gitHupProvider.getAccessToken(accessTokenDTO);
        GithupUser githupUser = gitHupProvider.getUser(accessToken);
        if(githupUser != null){

            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githupUser.getName());
            user.setAccountId(String.valueOf(githupUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());

            userMapper.insert(user);
            // 登陆成功 写入Cookies 和 session
            request.getSession().setAttribute("user",githupUser);
            // 跳转
            return "redirect:/";
        }else {
            // 登陆失败 重新登陆
            return "redirect:/";
        }
    }

}
