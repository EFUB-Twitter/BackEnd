package com.example.backend_efub_twitter.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;

	private static final String[] AUTH_WHITELIST = {
		// -- swagger ui
		"/v2/api-docs",
		"/v3/api-docs/**",
		"/configuration/ui",
		"/swagger-resources/**",
		"/configuration/security",
		"/swagger-ui.html",
		"/webjars/**",
		"/file/**",
		"/image/**",
		"/swagger/**",
		"/swagger-ui/**",
		// other public endpoints of your API may be appended to this array
		"/h2/**",
		"/h2-console/**"
	};

	@Bean
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.httpBasic().disable()
			.formLogin().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers("/api/v1/user/**").anonymous()
			.antMatchers("/api/v1/**").hasRole("USER")
			.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
				UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(AUTH_WHITELIST);
	}
}
