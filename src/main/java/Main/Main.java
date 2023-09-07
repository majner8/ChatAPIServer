package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EntityScan(basePackages={"User","Autorization"})
@ComponentScan({"Autorization","Security","User","Main"})
@EnableJpaRepositories(basePackages = {"User.Interface","Autorization.Service"})
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableAsync
public class Main {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	
}
