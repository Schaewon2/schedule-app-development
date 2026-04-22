package com.scheduleappdevelopment.schedule.entity;

import com.scheduleappdevelopment.common.entity.BaseEntity;
import com.scheduleappdevelopment.schedule.dto.ScheduleUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // 모든 field에 대한 Getter 자동 생성
@Entity // JPA가 관리하는 Entity 클래스로 정의 (DB table과 Mapping)
@Table(name = "schedules") // 실제 DB 테이블 이름을 "schedules"로 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 규정상 기본 생성자 필수, 무분별한 객체 생성 방지를 위해 PROTECTED 설정
public class Schedule extends BaseEntity {
    @Id // 테이블의 기본키(PK) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 전략을 DB의 AUTO_INCREMENT에 위임
    private Long id;

    @Column(nullable = false) // NOT NULL 제약조건 설정
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT") // TEXT 타입으로 설정 (긴 글 저장 가능)
    private String content;

    /**
     * 빌더 패턴을 이용한 생성자
     * 생성 시점에 필요한 데이터(title, content)를 명확하게 주입
     */
    @Builder
    public Schedule(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * Service Layer에서 사용할 일정 수정 메서드
     * @param requestDto 수정할 제목(title)과 내용(content)이 담긴 DTO
     */
    public void update(ScheduleUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
