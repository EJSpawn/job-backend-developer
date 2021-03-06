package com.project.jobbackenddeveloper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		//Definição de autenticação
        http
        	.csrf()
        	.disable()// <- Desabilita proteção para CSRF para fins de teste
            .authorizeRequests()
                .antMatchers("/", "/home").authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
    
	@Bean
    @Override
    @SuppressWarnings("deprecation")
    public UserDetailsService userDetailsService() {
    	//Usuários criados para simulação
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("pass")
                .roles("USER")
                .build();
        
        UserDetails admin =
                User.withDefaultPasswordEncoder()
                   .username("admin")
                   .password("pass")
                   .roles("ADMIN")
                   .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}