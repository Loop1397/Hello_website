package com.zerobase.fastlms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.zerobase.fastlms.member.service.MemberService;

import lombok.RequiredArgsConstructor;

//웹 시큐리티를 활성화하기 위해 두개의 어노테이션, 하나의 클래스를 상속받음
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final MemberService memberService;
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
	@Bean
	UserAuthenticationFailureHandler getFailureHandler() {
		return new UserAuthenticationFailureHandler();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		//http페이지의 권환을 설정할 수 있음
		
		/**
		 * "/"(루트), "/**"(루트 외의 모든 페이지)
		 * antMatchers("주소") : 설정을 적용할 페이지 지정
		 * permitAll() : 해당 페이지에는 권한 없이 접근할 수 있도록 설정함
		 * hasAuthority("권한 명") : 해당 권한 명을 가진 계정만 접근할 수 있도록 설정함
		 */
		
		
		http.authorizeRequests()
				.antMatchers(
						"/",
						"/hello",
						"/member/register",
						"/member/email_auth",
						"/member/find/password",
						"/member/reset/password",
						"/email/setting"
				)
				.permitAll();
		
		//admin이하의 모든 페이지는 ROLE_ADMIN이 설정되어있어야지만 접근할 수 있음.(adminYn = true)
		http.authorizeRequests()
				.antMatchers("/admin/**")
				.hasAuthority("ROLE_ADMIN");
		
		//권환이 없는 상태에서 특정 페이지에 접근했을 때 /error/denied로 자동 이동
		http.exceptionHandling()
				.accessDeniedPage("/error/denied");
		
		//로그인 관련
		//failureHandler() : 로그인에 실패했을 때 처리하는 함수
		http.formLogin()
				.loginPage("/member/login")
				.failureHandler(getFailureHandler())
				.permitAll();
		
		//로그아웃 관련
		//logoutSuccessUrl("~~") : 로그아웃에 성공한 후 해당 주소로 이동
		//invalidateHttpSession(true/false) : 로그아웃 후 세션을 초기화할지 말지 결정(true면 세션 초기화)
		http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true);
		
		super.configure(http);
	}
	
	
	//멤버의 정보를 넘겨주는 메소드
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(memberService)
				.passwordEncoder(getPasswordEncoder());
		
		
		super.configure(auth);
	}
	
	
}
