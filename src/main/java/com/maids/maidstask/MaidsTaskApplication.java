package com.maids.maidstask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MaidsTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaidsTaskApplication.class, args);
    }

}
