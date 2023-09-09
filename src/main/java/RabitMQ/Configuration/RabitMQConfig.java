package RabitMQ.Configuration;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabitMQConfig {

	@Value("RabitMQ.exchange")
	private String exchangeName;
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(this.exchangeName);
	}
	
}
