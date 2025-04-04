package com.example.scheduledevelop.dto.User;

import com.example.scheduledevelop.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SignUpResponseDto {

    private final Long userId;
    private final String userName;
    private final String email;
    private LocalDateTime createdAt;


    public SignUpResponseDto(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }
}
