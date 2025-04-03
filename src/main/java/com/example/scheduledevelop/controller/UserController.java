package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.SignUpRequestDto;
import com.example.scheduledevelop.dto.SignUpResponseDto;
import com.example.scheduledevelop.dto.UpdatePasswordDto;
import com.example.scheduledevelop.dto.UserResponseDto;
import com.example.scheduledevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 유저 생성
    @PostMapping
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto RequestDto) {

        SignUpResponseDto signupUser = userService.signup(RequestDto);

        return new ResponseEntity<>(signupUser, HttpStatus.CREATED);
    }

    // 유저 전체 조회
    @GetMapping
    public  ResponseEntity<List<UserResponseDto>> findAllUsers() {
        List<UserResponseDto> allUsers = userService.findAll();

        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    // 유저 비밀번호 변경
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordDto requestDto) {

        userService.updatePassword(id, requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
