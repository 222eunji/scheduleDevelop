package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.CreateScheduleRequestDto;
import com.example.scheduledevelop.dto.CreateScheduleResponseDto;
import com.example.scheduledevelop.dto.ScheduleResponseDto;
import com.example.scheduledevelop.dto.UpdateScheduleRequestDto;
import com.example.scheduledevelop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<CreateScheduleResponseDto> createSchedule(@RequestBody CreateScheduleRequestDto requestDto){

        CreateScheduleResponseDto schedule = scheduleService.createSchedule(requestDto);

        return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules() {

        List<ScheduleResponseDto> schedules = scheduleService.findAll();

        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    // 일정 수정(content)
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<Void> updateContent(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequestDto requestDto){

        scheduleService.updateContent(scheduleId, requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId){

        scheduleService.deleteSchedule(scheduleId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
