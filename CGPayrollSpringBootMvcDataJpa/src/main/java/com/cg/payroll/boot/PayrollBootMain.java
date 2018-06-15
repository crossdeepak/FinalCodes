package com.cg.payroll.boot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@SpringBootApplication(scanBasePackages={"com.cg.payroll"})
@EntityScan(basePackages="com.cg.payroll.beans")
@EnableJpaRepositories(basePackages="com.cg.payroll.daoservices")
@EnableWebMvc
public class PayrollBootMain extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(PayrollBootMain.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PayrollBootMain.class);
	}
}