package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * thrift支持http协议
 *
 * @author xf.chen
 * @date 2022/3/26 23:27
 * @since 1.0.0
 */
//@ServletComponentScan(basePackages = "com.example.test.servlet")
@SpringBootApplication
public class ThriftHttpServerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ThriftHttpServerBootstrap.class);
    }
}
