package com.webburguer.burguer3j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends  WebSecurityConfigurerAdapter{

	String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**","/build/**","/static/**","/templates/**","/images/**"
    };
	
	@Autowired
	RedireccionLogin redireccionLogin;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests()
        .antMatchers(resources).permitAll()  
        .antMatchers("/","/inicio").permitAll()
        .antMatchers("/registro").permitAll()
        .antMatchers("/sugerencia").permitAll()
        .antMatchers("/inicioa").access("hasRole('ADMIN')")
        .antMatchers("/inicioc").access("hasRole('USER')")
        .antMatchers("/editarUsuario/{id}").access("hasRole('ADMIN')")
        .antMatchers("/burguers").permitAll() 
        .antMatchers("/burguerInfo/{id}").permitAll() 
        .antMatchers("/carrito").permitAll()
        .antMatchers("/eliminar/carrito/{id}").permitAll() 
        .antMatchers("/orden").permitAll()
        .antMatchers("/getCarrito").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .permitAll()
            .successHandler(redireccionLogin)
            
            .failureUrl("/login?error=true")
            .usernameParameter("username")
            .passwordParameter("password")
            .and()
            .csrf().disable()
        .logout()
            .permitAll()
            .logoutSuccessUrl("/inicio?logout");
    }

	BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
    	//Especificar el encargado del login y encriptacion del password
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}