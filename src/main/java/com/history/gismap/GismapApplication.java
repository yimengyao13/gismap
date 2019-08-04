package com.history.gismap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.history.gismap.dao")

public class GismapApplication {

    public static void main(String[] args) {
        SpringApplication.run(GismapApplication.class, args);
    }

}
