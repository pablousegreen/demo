package com.example.demo;

import com.example.demo.util.EncodeDecodeUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters =
		{@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EncodeDecodeUtil.class)})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
