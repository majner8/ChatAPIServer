package Main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BeanSetting {

	
	@Bean
	public ObjectMapper initObjectMapper() {
		return new ObjectMapper();
	}
	
	 @Bean
	 public BCryptPasswordEncoder bCryptPasswordEncoder() {
	 return new BCryptPasswordEncoder();
	 }
}
