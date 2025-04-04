package com.example.scheduledevelop.dto.User;

import com.example.scheduledevelop.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private final Long userId;
    private final String userName;
    private final String email;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user);
    }



    //    public UserResponseDto(Long userId, String userName, String email,
//                           LocalDateTime createdAt, LocalDateTime modifiedAt, Long userId1, String userName1, String email1) {
//        this.userId = userId;
//        this.userName = userName;
//        this.email = email;
//    }

    //    public static UserResponseDto toDto(User user) {
//        return new UserResponseDto(
//                user.getUserId(),user.getUserName(), user.getEmail(),
//                user.getCreatedAt(),user.getModifiedAt());
//    }

}
