package tw.rc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.config.AuditingConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig {

	@Bean
	public SecurityFilterChain myFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.authorizeRequests()
					.requestMatchers("/login","/logout","/main").permitAll()
					.anyRequest().authenticated()
					.and()
						.formLogin()
						.loginPage("/login")
						.defaultSuccessUrl("/main", true)
						.permitAll()
					.and()
						.logout()
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login?logout")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.permitAll();

		return httpSecurity.build();
	}
	@Bean
	public UserDetailsService createUser() {
		UserDetails user1 = 
				User.withDefaultPasswordEncoder()
				.username("RC")
				.password("123456")
				.roles("USER")
				.build();
		UserDetails user2 = 
				User.withDefaultPasswordEncoder()
				.username("ispan")
				.password("123456")
				.roles("USER")
				.build();
		
		InMemoryUserDetailsManager manager =new InMemoryUserDetailsManager(user1,user2);
		
		return manager;
	}
	
	
	
	
	@Bean
	public AuthenticationManager createManager(AuthenticationConfiguration conf) throws Exception{
		return conf.getAuthenticationManager();
	}
	
}
