package com.example.scheduledevelop.dto.Schedule;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {

    private final String content;

    public UpdateScheduleRequestDto(String content) {
        this.content = content;
    }

}