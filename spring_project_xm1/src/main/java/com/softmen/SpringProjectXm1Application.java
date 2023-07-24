package com.softmen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //开启了对servlet组件支持 ，也就是javaweb技术可以使用
@SpringBootApplication
public class SpringProjectXm1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringProjectXm1Application.class, args);
    }

}
