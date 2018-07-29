package com.wx.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
// @EnableAsync
// @EnableTransactionManagement
// @EnableScheduling
// @PropertySource(value = {"classpath:application-dev.properties"})
public class Application implements WebMvcConfigurer {


	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

}
