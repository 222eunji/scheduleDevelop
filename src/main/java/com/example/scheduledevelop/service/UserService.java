package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.User.*;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void login(LoginRequestDto requestDto, HttpServletRequest request) {

        User findUser = userRepository.findUserByEmailOrElseThrow(requestDto);
        if(!findUser.getPassword().equals(requestDto.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공 시 세션에 저장
        HttpSession session = request.getSession(true);
        session.setAttribute("user", findUser);
    }

    public SignUpResponseDto signup(SignUpRequestDto requestDto) {

        User user = new User(requestDto.getUserName(), requestDto.getPassword(), requestDto.getEmail());

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser);
    }

    public List<UserResponseDto> findAll() {

        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toDto)
                .toList();
    }

    @Transactional
    public void updatePassword(Long id, UpdatePasswordDto requestDto) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        if(!findUser.getPassword().equals(requestDto.getOldPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        findUser.updatePassword(requestDto.getNewPassword());

    }

    public void deleteUser(Long id) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        userRepository.delete(findUser);

    }

}
