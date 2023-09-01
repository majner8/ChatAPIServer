package Main;

import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BeanSetting {

	
	@Bean
	public ObjectMapper initObjectMapper() {
		return new ObjectMapper();
	}
}
