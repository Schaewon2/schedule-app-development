package com.scheduleappdevelopment.schedule.dto;

import com.scheduleappdevelopment.schedule.entity.Schedule;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter // 데이터를 꺼내기 위한 Getter
public class ScheduleUpdateResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    @Builder // 빌더 패턴 : 객체 생성 시 field 명을 명시하여 가독성을 높인다.
    public ScheduleUpdateResponseDto(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    /**
     * 정적 팩토리 메서드 : Entity를 기반으로 DTO 객체를 생성한다.
     * 인스턴스 메서드(toEntity)와 달리,
     * DB에서 가져온 Entity를 DTO로 변환하여 새로 찍어내는 역할이므로 static을 사용한다.
     */
    public static ScheduleUpdateResponseDto of(Schedule schedule) {
        return ScheduleUpdateResponseDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .createdAt(schedule.getCreatedAt())
                .modifiedAt(schedule.getModifiedAt())
                .build();
    }
}
