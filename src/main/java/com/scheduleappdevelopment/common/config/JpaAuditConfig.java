package com.scheduleappdevelopment.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JpaAuditConfig: JPA Auditing(자동 기록) 기능을 활성화하는 설정 클래스
 */
@Configuration // 스프링 설정 클래스임을 명시
@EnableJpaAuditing // JPA의 감시(Auditing) 기능을 활성화 (이게 없으면 @CreatedDate 등이 작동 안 함)
public class JpaAuditConfig {

    /**
     * auditorAware: 위에서 만든 AuditorAwareImpl을 스프링 빈(Bean)으로 등록
     * 등록해두면 JPA가 "누가 만들었지?"라고 물어볼 때 이 객체가 대답함
     */
    @Bean
    public AuditorAware<Long> auditorAware() {
        return new AuditorAwareImpl();
    }
}