package com.telecom.shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author admin
 */
@SpringBootApplication
@Slf4j
public class ShopApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	@Override
	public void run(String... strings) {
		log.info("服务器已起动---");
	}
}
