package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.SignUpRequestDto;
import com.example.scheduledevelop.dto.SignUpResponseDto;
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

    @PostMapping
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto RequestDto) {

        SignUpResponseDto signupUser = userService.signup(RequestDto);

        return new ResponseEntity<>(signupUser, HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<List<UserResponseDto>> findAllUsers() {
        List<UserResponseDto> allUsers = userService.findAll();

        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }


}
