package com.hunting.edison.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * application启动器
 * @author Louis & Edison
 * @date Oct 29, 2018
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"com.hunting.edison.admin"})
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}
