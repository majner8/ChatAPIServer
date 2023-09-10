package RabitMQ.Management;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="Singleton")
public class RabitMQQueueCreationManagement implements QueueManagement{

	@Autowired
	private RabitMQDatabase rabitMqDatabase;
	@Autowired
	private RabbitTemplate rabitMQ;
	//have to be create as bean 
	@Autowired
	private TopicExchange topicExchange;
	@Override
	public void createDeviceQueue(String deviceUUID, String userUUID) {
		// TODO Auto-generated method stub
		this.createUserQueue(userUUID);
		
		
		if(this.rabitMqDatabase.UserDeviceQueueCreation(userUUID,deviceUUID)) {
			//have to create new one
			this.rabitMQ.execute(Chanel->{
				HashMap <String,Object> parametr=new HashMap<String,Object>();
				parametr.put("x-queue-mode", "lazy");
				
				Chanel.queueDeclare(userUUID+deviceUUID, true, false, false, parametr);
				
				return null;
			});
		}
		
		
		
		

		
	}

	@Override
	public void removeDeviceQueue(String deviceUUID, String userUUID) {
		// TODO Auto-generated method stub
		synchronized(this) {
			
		}
	}
	@Override
	public void createUserQueue(String userUUID) {
		// TODO Auto-generated method stub
		if(this.rabitMqDatabase.UserQueueCreation(userUUID)) {
			//have to create new one

			//load all active chat to bind it
			List<String >chat=this.rabitMqDatabase.getActiveUserChatUUID();
			
			
			this.rabitMQ.execute(Chanel->{
				Chanel.queueDeclare(userUUID, true, false, false, null);
				chat.forEach(S->{
					try {
						Chanel.queueBind(userUUID, this.topicExchange.getName(), S);
					} catch (IOException e) {
						// TODO Auto-generated catch block
				        throw new RuntimeException("Failed to declare queue or bind queue to exchange", e);
					}
				});
				return null;
			});
		}
		
	}

	@Override
	public void removeUserQueue(String userUUID) {
		// TODO Auto-generated method stub
		synchronized(this) {
			
		}
	}
	

}
