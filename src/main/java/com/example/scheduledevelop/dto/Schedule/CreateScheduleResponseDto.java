package com.example.scheduledevelop.dto.Schedule;

import lombok.Getter;

@Getter
public class CreateScheduleResponseDto {

    private final Long scheduleId;
    private final String title;
    private final String content;

    public CreateScheduleResponseDto(Long scheduleId, String title, String content) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.content = content;
    }
}
