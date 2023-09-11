package Main;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ChatManagement.ChatService.Chat;

@Configuration
public class BeanSetting {

	
	 @Bean
	    public ObjectMapper objectMapper() {
	     return new ObjectMapper();
	    }
	
	 @Bean
	 public BCryptPasswordEncoder bCryptPasswordEncoder() {
	 return new BCryptPasswordEncoder();
	 }
	 
	 @Bean
	 public ActiveChatHashMap ActiveChat() {
		 return new ActiveChatHashMap();
	 }
	 
	 public static class ActiveChatHashMap extends HashMap<String, Chat>{
		 
		 public ActiveChatHashMap() {
			 super();
		 }
	 }
}
