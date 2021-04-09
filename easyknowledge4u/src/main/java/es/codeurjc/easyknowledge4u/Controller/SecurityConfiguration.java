package es.codeurjc.easyknowledge4u.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import es.codeurjc.easyknowledge4u.Repositories.UserRepositoryAuthenticationProvider;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;
	
	  
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
		
		//public
		 http.authorizeRequests().antMatchers("/index").permitAll();
		 http.authorizeRequests().antMatchers("/login").permitAll();
	     http.authorizeRequests().antMatchers("/loginerror").permitAll();
	     http.authorizeRequests().antMatchers("/logout").permitAll();
		 http.authorizeRequests().antMatchers("/register").permitAll();
		 http.authorizeRequests().antMatchers("/contacto").permitAll();
		 http.authorizeRequests().antMatchers("/comprobarLogin").permitAll();
		 http.authorizeRequests().antMatchers("/registro").permitAll();
		 http.authorizeRequests().antMatchers("/guardarContacto").permitAll();
		 http.authorizeRequests().antMatchers("/contacto-enviado").permitAll();
		 http.authorizeRequests().antMatchers("/inicio-sesion").permitAll();
		 
	 
		//private		 
		 http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
		 http.authorizeRequests().antMatchers("/adminPanel").hasAnyRole("ADMIN");
		 http.authorizeRequests().antMatchers("/home").hasAnyRole("USER");
		 http.authorizeRequests().antMatchers("/cursos").hasAnyRole("USER");
		 http.authorizeRequests().antMatchers("/añadirCursoM").hasAnyRole("USER");
		 http.authorizeRequests().antMatchers("/añadirCursoI").hasAnyRole("USER");
		 http.authorizeRequests().antMatchers("/añadirCursoE").hasAnyRole("USER");
		 http.authorizeRequests().anyRequest().authenticated();
		
		
		 
			// Login form
		 http.formLogin().loginPage("/login");
		 http.formLogin().usernameParameter("username");
		 http.formLogin().passwordParameter("password");
		 http.formLogin().defaultSuccessUrl("/index");
		 http.formLogin().failureUrl("/loginerror");
				
		
	
		 // Logout
		 http.logout().logoutUrl("/logout");
		 http.logout().logoutSuccessUrl("/index");
		 
		//CSRF
		//  http.csrf().disable();
	
	}	
		
	
	@Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	 
	 // User 
		auth.inMemoryAuthentication().withUser("user@gmail.com").password("pass").roles("USER");
		
	// ADMIN
		
		auth.inMemoryAuthentication().withUser("admin@gmail.com").password("adminpass").roles("ADMIN");
		
		auth.authenticationProvider(authenticationProvider);
		
	}
	

	
	
}
