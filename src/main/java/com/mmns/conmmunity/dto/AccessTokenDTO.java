package com.mmns.conmmunity.dto;


import lombok.Data;

@Data
public class AccessTokenDTO {
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
    private String state;

}
