package com.alu.itoken.service.sso;




import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication//(scanBasePackages = "com.alu.itoken",exclude = DataSourceAutoConfiguration.class )
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages= {"com.alu.itoken.service.sso.mapper"})
public class ServiceSsoApplication {
   public static void main(String[] args) {
	SpringApplication.run(ServiceSsoApplication.class, args);
}
}
