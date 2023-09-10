package RabitMQ.Management;


/**Interface manage creating and removing queue and set Appropriate Binding
 * Interface work in thread safe metod*/
public interface QueueManagement {

	
	public void createDeviceQueue(String deviceUUID,String userUUID);
	
	public void removeDeviceQueue(String deviceUUID,String userUUID);
	
	public void createUserQueue(String userUUID);
	
	public void removeUserQueue(String userUUID);
	
	
}
