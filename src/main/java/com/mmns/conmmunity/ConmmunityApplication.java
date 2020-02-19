package com.mmns.conmmunity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mmns.conmmunity.mapper")
public class ConmmunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConmmunityApplication.class, args);
    }

}
