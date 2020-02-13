package com.mmns.conmmunity.provider;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSON;
import com.mmns.conmmunity.dto.AccessTokenDTO;
import com.mmns.conmmunity.dto.GithupUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Component
public class GithupProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        System.out.println(accessTokenDTO);

        Map<String,Object> map = new HashMap<>();
        map.put("client_id",accessTokenDTO.getClientId());
        map.put("client_secret",accessTokenDTO.getClientSecret());
        map.put("code",accessTokenDTO.getCode());
        map.put("redirect_uri",accessTokenDTO.getRedirectUri());
        map.put("state",accessTokenDTO.getState());

        //将对象转成json使用fastjson
        RequestBody body = RequestBody.create(JSON.toJSONString(map),mediaType);

        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String result=response.body().string();
            String token=result.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithupUser getUser(String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithupUser githupUser = JSON.parseObject(string, GithupUser.class);
            return githupUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
