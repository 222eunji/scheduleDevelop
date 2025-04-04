package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.User.*;
import com.example.scheduledevelop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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

    // 로그인 요청
    @GetMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequestDto requestDto, HttpServletRequest request){
        userService.login(requestDto, request);
        return new ResponseEntity<>("로그인 성공",HttpStatus.OK);

    }

    // 유저 생성
    @PostMapping("/signUp")
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

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
