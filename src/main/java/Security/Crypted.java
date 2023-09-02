package Security;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Crypted implements CryptedServiceInterface {

	private	BasicTextEncryptor jasypt;

	public Crypted() {
		this.jasypt=new BasicTextEncryptor();
		this.jasypt.setPassword("_sdarew?aěš4%");
	}

	@Override
	public String CryptId(long Id) {
		// TODO Auto-generated method stub
		return this.jasypt.encrypt(String.valueOf(Id));
	}

	@Override
	public long DecryptId(String character) {
		// TODO Auto-generated method stub
	
		return Long.parseLong(this.jasypt.decrypt(character));
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}


}
