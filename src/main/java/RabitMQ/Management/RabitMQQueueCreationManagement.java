package RabitMQ.Management;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class RabitMQQueueCreationManagement implements QueueManagement{

	@Autowired
	private AmqpAdmin rabAdmin;
	@Override
	public void createDeviceQueue(String deviceUUID, String userUUID) {
		// TODO Auto-generated method stub
		synchronized(this) {
			this.createUserQueue(userUUID);
			Queue qu=QueueBuilder.durable(userUUID+deviceUUID)
					.withArgument("x-queue-mode", "lazy")
					.build();	
		}
	}

	@Override
	public void removeDeviceQueue(String deviceUUID, String userUUID) {
		// TODO Auto-generated method stub
		synchronized(this) {
			
		}
	}
	private RabbitTemplate x=null;
	@Override
	public void createUserQueue(String userUUID) {
		// TODO Auto-generated method stub
		synchronized(this) {
			x.execute(c->{
				
				return null;
			});
			if(this.rabAdmin.getQueueProperties(userUUID)==null){
				//queue does not exist, have to create and bind new one
				Queue qu=new Queue("myQueue", true);				
			}
		}
	}

	@Override
	public void removeUserQueue(String userUUID) {
		// TODO Auto-generated method stub
		synchronized(this) {
			
		}
	}
	

}
