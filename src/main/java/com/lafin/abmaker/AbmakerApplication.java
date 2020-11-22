package com.lafin.abmaker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
@SpringBootApplication
@MapperScan(value={"com.lafin.abmaker.mapper"})
public class AbmakerApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(AbmakerApplication.class, args);
    }
}
