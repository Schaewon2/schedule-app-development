package com.scheduleappdevelopment.schedule.dto;

import com.scheduleappdevelopment.schedule.entity.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor // final field를 생성해주는 Annotation (scheduleDtoList)
@Getter // 데이터를 꺼내기 위한 Getter
public class ScheduleListResponseDto {

    private final List<ScheduleDto> scheduleDtoList;

    /**
     * 정적 팩토리 메서드 : Stream을 사용하여 각 Schedule Entity를 ScheduleDto로 Mapping한다.
     */
    public static ScheduleListResponseDto of(List<Schedule> scheduleList) {
        List<ScheduleDto> dtoList = scheduleList.stream()// 1. Entity List를 Stream으로 변환한다.
                .map(ScheduleDto::of) // 2. 각 Entity를 DTO로 Mapping한다.
                .toList(); // 3. 변환된 DTO들을 List로 수정한다.
        return new ScheduleListResponseDto(dtoList); // 4. 최종 응답 DTO에 담아서 반환한다.
        /**
         * List를 그대로 반환하지 않고 DTO로 감싸서 반환하는 이유
         * 1. 확장성: 추후 '전체 개수', '페이지 정보' 등 추가 데이터를 담기 쉬워진다.
         * 2. 유연성: 응답 규격을 객체({})로 유지하여 프론트엔드와 통신 시 안정성을 높인다.
         */
    }

    @Getter // 데이터를 꺼내기 위한 Getter
    public static class ScheduleDto {
        private final Long id;
        private final String title;
        private final String content;
        private final LocalDateTime createdAt;
        private final LocalDateTime modifiedAt;

        @Builder // 빌더 패턴 : 객체 생성 시 field 명을 명시하여 가독성을 높인다.
        public ScheduleDto(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
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
        public static ScheduleDto of(Schedule schedule) {
            return ScheduleDto.builder()
                    .id(schedule.getId())
                    .title(schedule.getTitle())
                    .content(schedule.getContent())
                    .createdAt(schedule.getCreatedAt())
                    .modifiedAt(schedule.getModifiedAt())
                    .build();
        }
    }
}
