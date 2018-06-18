package com.letcafe.portscanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.letcafe")
@SpringBootApplication
public class PortScannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortScannerApplication.class, args);
    }
}
