package com.ping;

import com.ping.config.InitApplicationListener;
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
		SpringApplication application = new SpringApplication(SBSystemApplication.class);
		application.addListeners(new InitApplicationListener("init.properties"));
		application.run(args);
	}
}
