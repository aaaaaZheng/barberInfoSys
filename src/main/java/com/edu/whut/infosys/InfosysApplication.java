package com.edu.whut.infosys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class InfosysApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfosysApplication.class, args);
    }

}
