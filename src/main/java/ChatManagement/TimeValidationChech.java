package ChatManagement;

import java.util.HashSet;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimeValidationChech {

	private HashSet<IsStillActive> activeObject;
	
    @Scheduled(fixedRate = 180000 )  
	public void TimeChech() {
    	long actualTime=System.currentTimeMillis();
    	this.activeObject.forEach(value->{
    		value.TimeChech(actualTime);
    	});
	}
    
    public void addNewObjectToVerify(IsStillActive object) {
    	this.activeObject.add(object);
    }
    public void removeObjectFromVerification(IsStillActive object) {
    	this.activeObject.remove(object);
    }
	
	public static interface IsStillActive{
		/** it is recomended to implements and process this metod as Async, to increare performance 
		 * @param actualTime
		 */
		public void TimeChech(long actualTime);
	}
}
