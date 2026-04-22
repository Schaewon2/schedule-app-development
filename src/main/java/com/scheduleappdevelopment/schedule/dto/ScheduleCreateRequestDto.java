package com.scheduleappdevelopment.schedule.dto;

import com.scheduleappdevelopment.schedule.entity.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // JSON 데이터를 DTO 객체로 변환할 때(Jackson 라이브러리) 기본 생성자가 반드시 필요
@Getter // 데이터를 꺼내기 위한 Getter
public class ScheduleCreateRequestDto {

    private String title;
    private String content;

    @Builder // 빌더 패턴 : 객체 생성 시 field 명을 명시하여 가독성을 높인다.
    public ScheduleCreateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * 인스턴스 메서드 : DTO가 가진 데이터를 기반으로 Entity 객체를 생성한다.
     */
    public Schedule toEntity() {
        return Schedule.builder().
                title(this.title)
                .content(this.content)
                .build();
    }
}
