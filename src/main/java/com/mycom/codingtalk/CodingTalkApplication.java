package com.mycom.codingtalk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mycom.codingtalk.mapper")
public class CodingTalkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodingTalkApplication.class, args);
    }

}
