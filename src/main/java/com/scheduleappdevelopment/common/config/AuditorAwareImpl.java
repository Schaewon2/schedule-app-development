package com.scheduleappdevelopment.common.config;

import org.springframework.data.domain.AuditorAware;
import java.util.Optional;

/**
 * AuditorAwareImpl: 데이터를 생성하거나 수정할 때 '누가' 했는지 정보를 제공하는 클래스
 */
public class AuditorAwareImpl implements AuditorAware<Long> {

    /**
     * getCurrentAuditor: 현재 로그인한 사용자의 ID를 반환하는 메서드
     * @return 현재는 로그인 기능 구현 전이므로, 임시로 관리자 ID인 1L을 반환하였다.
     */
    @Override
    public Optional<Long> getCurrentAuditor() {
        // Optional.of(1L): "이 데이터는 1번 사용자가 만든 거야"라고 고정해서 알려준다.
        return Optional.of(1L);
    }
}