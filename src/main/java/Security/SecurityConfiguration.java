package Security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	public static final String userIsActiveRole="Role_active";

	public static final String jwtTokenPreflix="Bearer ";
	
	 @Override
	 protected void configure(HttpSecurity http)throws Exception {
		 
		 
		 http.csrf().disable()
		 .formLogin().disable()
		 .logout().disable()	
		 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 .and() 
		 .authorizeRequests()
		 .antMatchers("/autorization/**").permitAll()
		 .anyRequest().authenticated()
		 
		 .and()
		 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) 
		 .addFilterBefore(this.emailPass,JwtTokenFilter.class);
		 
		 
	 }


}
