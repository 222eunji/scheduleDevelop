package com.example.scheduledevelop.dto.Schedule;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private final String title;
    private final String content;
    private final Long userId;

    public CreateScheduleRequestDto(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
}
