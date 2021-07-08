package cn.lee.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ExamplePlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamplePlusApplication.class, args);
    }

}
