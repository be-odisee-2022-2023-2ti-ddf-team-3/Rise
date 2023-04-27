package be.odisee.demoplanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

// In Spring Security 5.7.0-M2 we deprecated the WebSecurityConfigurerAdapter
// as we encourage users to move towards a component-based security configuration
// HV en nu (v 6.0.2) is ie zelfs verwijderd => we hebben een andere oplossing nodig

// Bovendien ...
// In Spring Security 6.0, antMatchers() have been removed from the API.

// En ...
// Regarding deprecated annotation @EnableGlobalMethodSecurity
// it was replaced with @EnableMethodSecurity.
// The rationale behind this change is that with @EnableMethodSecurity
// property prePostEnabled needed to enable use of @PreAuthorize/@PostAuthorize and @PreFilter/@PostFilter
// is by default set to true.
//So you no longer need to write prePostEnabled = true, etc.


@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class SecurityConfig {
	
	@Autowired
	DataSource datasource;

	@Bean
	public UserDetailsManager authenticateUsers() {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(datasource);

		users.setUsersByUsernameQuery("select emailadres, paswoord, true as enabled from personen where emailadres=?");
		users.setAuthoritiesByUsernameQuery("select emailadres, type as authority from personen where emailadres=?");
		return users;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/login*").permitAll()
						.requestMatchers("/logout*").permitAll()
						.requestMatchers("/css/**").permitAll()
						.requestMatchers("/logoutSuccess*").permitAll()
						.requestMatchers("/accessDenied*").permitAll()
						.requestMatchers("/admin/**").hasAuthority("Administrator")
						.requestMatchers("/organisator/**").hasAuthority("Organisator")
						.requestMatchers("/brainstorm/**").hasAnyAuthority("Facilitator","Deelnemer")
						.requestMatchers("/**").authenticated()
				)
				.formLogin(form -> form
						.loginPage("/login")
						.defaultSuccessUrl("/index.html")
					)
				.exceptionHandling().accessDeniedPage("/403.html");
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
