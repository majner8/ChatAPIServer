package RabitMQ.Management;

import java.util.List;

public interface RabitMQDatabase {


	/**Metod set last time of used queue to actual time
	 *@return true if queue do not exist, and have to be created, in database it will mark as exist
	 *@return false if queue exist */
	public boolean UserQueueCreation(String userUUID);
	
	/**Metod set last time of used queue to actual time
	 *@return true if queue do not exist, and have to be created, in database it will mark as exist
	 *@return false if queue exist */
	public boolean UserDeviceQueueCreation(String userUUID,String deviceUUID);

	/**Metod return list of chat uuid, will be make after as repository */
	public List<String> getActiveUserChatUUID();

}
