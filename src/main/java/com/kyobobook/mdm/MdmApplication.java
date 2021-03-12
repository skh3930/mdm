package com.kyobobook.mdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MdmApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdmApplication.class, args);
	}
}
