package com.example.security2app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		System.out.println("* userDetailsService");
		
		UserDetails admin = User
							.builder()
							.username("admin")
							.password(passwordEncoder().encode("123"))
							.roles("ADMIN")
							.build();
		UserDetails user = User.builder().username("user").password(passwordEncoder().encode("123")).roles("USER").build();
		
		return new InMemoryUserDetailsManager(admin, user);
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
            	.requestMatchers("/admin/**").hasRole("ADMIN")
            	.requestMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.permitAll())
            .logout(form -> form.permitAll());
        
        return http.build();
    }

}
