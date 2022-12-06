package com.xb.library.management.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** @author xiabiao/ */
@SpringBootApplication
@EnableSwagger2
@ComponentScan(
    basePackages = {
      "com.xb.library.management.system",
      "com.xb.library.management.system.api",
      "com.xb.library.management.system.configuration"
    })
@EntityScan(basePackages = {"com.xb.library.management.system.domain"})
@EnableTransactionManagement
public class LibraryApp {

  public static void main(String[] args) {
    new SpringApplication(LibraryApp.class).run(args);
  }
}
