package com.mmns.conmmunity.controller;

import com.mmns.conmmunity.dto.AccessTokenDTO;
import com.mmns.conmmunity.dto.GithupUser;
import com.mmns.conmmunity.provider.GithupProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

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

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) throws IOException {
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
        GithupUser user = gitHupProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }

}
