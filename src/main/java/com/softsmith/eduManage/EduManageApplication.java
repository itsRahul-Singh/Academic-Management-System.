package com.softsmith.eduManage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class})
public class EduManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduManageApplication.class, args);
	}

}
