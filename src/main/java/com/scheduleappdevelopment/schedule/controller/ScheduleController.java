package com.scheduleappdevelopment.schedule.controller;

import com.scheduleappdevelopment.schedule.dto.*;
import com.scheduleappdevelopment.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // @Controller + @ResponseBody Annotation
@RequestMapping("/schedules") // 공통 Mapping Annotation
@RequiredArgsConstructor // final field를 생성해주는 Annotation (scheduleService)
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 일정 생성 API
     * POST /schedules
     * @return 생성된 일정 데이터, 201 Created
     */
    @PostMapping
    public ResponseEntity<ScheduleCreateResponseDto> createScheduleAPI(@RequestBody ScheduleCreateRequestDto requestDto) {
        ScheduleCreateResponseDto responseDto = scheduleService.createSchedule(requestDto);
        ResponseEntity<ScheduleCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        return response;
    }

    /**
     * 일정 전체 조회 API
     * GET /schedules
     * @return 전체 일정 목록 데이터, 200 OK
     */
    @GetMapping
    public ResponseEntity<ScheduleListResponseDto> getScheduleListAPI() {
        ScheduleListResponseDto responseDto = scheduleService.getScheduleList();
        ResponseEntity<ScheduleListResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * 일정 단건 조회 API
     * GET /schedules/{scheduleId}
     * @param scheduleId 조회할 일정의 식별자
     * @return 해당 일정 데이터, 200 OK
     */
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleListResponseDto.ScheduleDto> getScheduleAPI(@PathVariable Long scheduleId) {
        ScheduleListResponseDto.ScheduleDto responseDto = scheduleService.getSchedule(scheduleId);
        ResponseEntity<ScheduleListResponseDto.ScheduleDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * 일정 수정 API
     * PUT /schedules/{scheduleId}
     * @param scheduleId 수정할 일정의 식별자
     * @param requestDto 수정할 데이터 내용
     * @return 수정된 일정 데이터, 200 OK
     */
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponseDto> updateScheduleAPI(
            @PathVariable Long scheduleId, @RequestBody ScheduleUpdateRequestDto requestDto) {
        ScheduleUpdateResponseDto responseDto = scheduleService.updateSchedule(scheduleId, requestDto);
        ResponseEntity<ScheduleUpdateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * 일정 삭제 API
     * DELETE /schedules/{scheduleId}
     * @param scheduleId 삭제할 일정의 식별자
     * @return 204 No Content
     */
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteScheduleAPI(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return response;
    }
}
