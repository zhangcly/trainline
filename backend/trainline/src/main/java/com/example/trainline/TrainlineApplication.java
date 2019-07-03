package com.example.trainline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 *  启动类
 * </p>
 *
 * @author zhangc
 * @since 2019-06-26
 */
@MapperScan("com.example.trainline.mapper")
@SpringBootApplication
public class TrainlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainlineApplication.class, args);
    }

}
