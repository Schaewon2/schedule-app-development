package com.scheduleappdevelopment.schedule.service;

import com.scheduleappdevelopment.schedule.dto.*;
import com.scheduleappdevelopment.schedule.entity.Schedule;
import com.scheduleappdevelopment.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 'Service'를 상징하는 Annotation
@RequiredArgsConstructor // final field를 생성해주는 Annotation (scheduleRepository)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    /**
     * 일정 생성 서비스
     * 1. Controller에서 요청 데이터(ScheduleCreateRequestDto) 받아오기
     * 2. 받아온 데이터로 schedule Entity 만들기
     * 3. scheduleRepository에 schedule Entity 저장하기
     * 4. 저장된 schedule Entity (savedSchedule) 반환받기
     * 5. 응답 데이터(ScheduleCreateResponseDto) 반환하기
     *
     * requestBody
     * {
     *    "title" : "운동하기",
     *    "content" : "스쿼트 30회 실시"
     * }
     *
     * responseBody
     * {
     *     "id": 6,
     *     "title": "운동하기",
     *     "content": "스쿼트 30회 실시",
     *     "createdAt": "2026-04-22T23:29:49.685869",
     *     "modifiedAt": "2026-04-22T23:29:49.685869"
     * }
     */
    @Transactional
    // 1. Controller에서 요청 데이터(ScheduleCreateRequestDto) 받아오기
    public ScheduleCreateResponseDto createSchedule(ScheduleCreateRequestDto requestDto) {
        // 2. 받아온 데이터로 schedule Entity 만들기
        Schedule schedule = requestDto.toEntity();
        // 3. scheduleRepository에 schedule Entity 저장하기
        // 4. 저장된 schedule Entity (savedSchedule) 반환받기
        Schedule savedSchedule = scheduleRepository.save(schedule);
        // 5. 응답 데이터(ScheduleCreateResponseDto) 반환하기
        return ScheduleCreateResponseDto.of(savedSchedule);
    }

    /**
     * 일정 전체 조회 서비스
     * 1. scheduleRepository에서 모든 Schedule Entity를 조회하여 scheduleList에 담아주기
     * 2. 조회된 Entity List를 응답용 DTO(ScheduleListResponseDto)로 변환하고, 반환하기
     *
     * responseBody
     * {
     *     "scheduleDtoList": [
     *         {
     *             "id": 6,
     *             "title": "운동하기",
     *             "content": "스쿼트 30회 실시",
     *             "createdAt": "2026-04-22T23:29:49.685869",
     *             "modifiedAt": "2026-04-22T23:29:49.685869"
     *         },
     *         {
     *             "id": 7,
     *             "title": "공부하기",
     *             "content": "SQL JOIN 문법 공부하기",
     *             "createdAt": "2026-04-23T00:32:27.323146",
     *             "modifiedAt": "2026-04-23T00:32:27.323146"
     *         }
     *     ]
     * }
     */
    @Transactional(readOnly = true) // 읽기 전용
    public ScheduleListResponseDto getScheduleList() {
        // 1. scheduleRepository에서 모든 Schedule Entity를 조회하여 scheduleList에 담아주기
        List<Schedule> scheduleList = scheduleRepository.findAll();
        // 2. 조회된 Entity List를 응답용 DTO(ScheduleListResponseDto)로 변환하고, 반환하기
        return ScheduleListResponseDto.of(scheduleList);
    }

    /**
     * 일정 단건 조회 서비스
     * 1. scheduleId를 통해 Repository에서 특정 Entity를 조회한다.
     * 2. 조회 결과가 없을 경우(null) 예외(IllegalArgumentException)를 발생시킨다.
     * 3. 조회된 Entity를 응답용 DTO(ScheduleDto)로 변환하여 반환한다.
     *
     * Parameter : scheduleId = 7
     *
     * responseBody
     * {
     *     "id": 7,
     *     "title": "공부하기",
     *     "content": "SQL JOIN 문법 공부하기",
     *     "createdAt": "2026-04-23T00:32:27.323146",
     *     "modifiedAt": "2026-04-23T00:32:27.323146"
     * }
     */
    @Transactional(readOnly = true) // 읽기 전용
    // 1. scheduleId를 통해 Repository에서 특정 Entity를 조회한다.
    public ScheduleListResponseDto.ScheduleDto getSchedule(Long scheduleId) {
        // 2. 조회 결과가 없을 경우(null) 예외(IllegalArgumentException)를 발생시킨다.
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        // 3. 조회된 Entity를 응답용 DTO(ScheduleDto)로 변환하여 반환한다.
        return ScheduleListResponseDto.ScheduleDto.of(schedule);
    }

    /**
     * 일정 수정 서비스
     * 1. Parameter로 받은 scheduleId를 사용하여 수정할 Entity를 조회하기
     * 2. 조회 결과가 없을 경우 예외를 발생시켜 수정 절차 중단하기
     * 3. 받아온 요청 데이터(requestDto)로 Entity의 필드 값 업데이트하기 (Dirty Checking)
     * 4. 수정된 Entity를 응답용 DTO(ScheduleUpdateResponseDto)로 변환하여 반환하기
     *
     * Parameter : scheduleId = 7
     *
     * requestBody
     * {
     *     "title" : "공부 그만하기",
     *     "content" : "지쳤던 뇌를 휴식하게끔 하고 새로운 하루 시작하기"
     * }
     *
     * responseBody
     * {
     *     "id": 7,
     *     "title": "공부 그만하기",
     *     "content": "지쳤던 뇌를 휴식하게끔 하고 새로운 하루 시작하기",
     *     "createdAt": "2026-04-23T00:32:27.323146",
     *     "modifiedAt": "2026-04-23T00:57:05.318847"
     * }
     */
    @Transactional
    public ScheduleUpdateResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto requestDto) {
        // 1. Parameter로 받은 scheduleId를 사용하여 수정할 Entity를 조회하기
        // 2. 조회 결과가 없을 경우 예외를 발생시켜 수정 절차 중단하기
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        // 3. 받아온 요청 데이터(requestDto)로 Entity의 필드 값 업데이트하기 (Dirty Checking)
        schedule.update(requestDto);
        // 4. 수정된 Entity를 응답용 DTO(ScheduleUpdateResponseDto)로 변환하여 반환하기
        return ScheduleUpdateResponseDto.of(schedule);
    }

    /**
     * 일정 삭제 서비스
     * 1. 파라미터로 받은 scheduleId를 사용하여 삭제할 Entity가 존재하는지 확인하기
     * 2. 존재하지 않을 경우 예외를 발생시켜 삭제 절차 중단하기
     * 3. Repository를 통해 해당 ID를 가진 데이터를 DB에서 삭제하기
     *
     * Parameter : scheduleId = 7
     * Body : 없음 (void)
     */
    @Transactional
    // 1. 파라미터로 받은 scheduleId를 사용하여 삭제할 Entity가 존재하는지 확인하기
    // 2. 존재하지 않을 경우 예외를 발생시켜 삭제 절차 중단하기
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        // 3. Repository를 통해 해당 ID를 가진 데이터를 DB에서 삭제하기
        scheduleRepository.deleteById(scheduleId);
    }
}
