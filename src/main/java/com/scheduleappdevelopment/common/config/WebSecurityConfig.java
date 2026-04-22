package com.scheduleappdevelopment.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * WebSecurityConfig: 스프링 시큐리티(보안) 관련 설정 클래스
 */
@Configuration
@EnableWebSecurity // 스프링 시큐리티 기능을 활성화
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. CSRF 보안 기능 비활성화: 테스트 단계(Postman)에서 요청을 편하게 보내기 위함
                .csrf(AbstractHttpConfigurer::disable)

                // 2. 접근 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/schedules/**").permitAll() // /schedules로 시작하는 주소는 로그인 없이 허용
                        .anyRequest().authenticated()               // 그 외의 주소는 무조건 로그인(인증) 필요
                )

                // 3. 기본 로그인 폼 및 HTTP 기본 인증 비활성화: 우리가 만든 API만 쓸 것이므로 불필요한 기능 제거
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }
}