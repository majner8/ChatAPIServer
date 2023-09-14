package Authorization.Controler;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import DTO.Autorization.AutorizationRequestDTO;
import DTO.Autorization.TokenDTO;
import Entity.AutorizationEntity.SecurityMonitoring;

@Component
@Aspect
public class RegistrationLoggingAspect {

	@Autowired
	private EntityManager manager;
	
	/**Metod log users attemp of autorization */
	@AfterReturning(
		    pointcut = "execution(* Autorization.Controler.AutorizationControler.register(..)) && args(value, value1) "
		    + "|| execution(* Autorization.Controler.AutorizationControler.login(..)) && args(value, value1)",
		    returning = "response")

    public void AutorizationMonitoringAttemp(JoinPoint joinPoint, AutorizationRequestDTO value,HttpServletRequest value1, ResponseEntity<TokenDTO> response) {
    	SecurityMonitoring set=new SecurityMonitoring();
    	set.setAttempSucesful((response.getStatusCode()==HttpStatus.OK?true:false));
    	set.setCountryPreflix(value.getCountryPreflix());
    	set.setPhone(value.getPhone());
    	set.setEmail(value.getEmail());
    	set.setIPV4Adress(value1.getRemoteAddr());
    	this.manager.persist(set);
    }
}
