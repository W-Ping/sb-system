package com.ping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author thinkpad
 * @date 2019/7/12 18:37
 * @see
 */
@MapperScan(basePackages = {"com.ping.mapper"})
@SpringBootApplication
public class SBSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(SBSystemApplication.class, args);
	}
}
