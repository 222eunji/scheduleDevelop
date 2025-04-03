package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.SignUpRequestDto;
import com.example.scheduledevelop.dto.SignUpResponseDto;
import com.example.scheduledevelop.dto.UpdatePasswordDto;
import com.example.scheduledevelop.dto.UserResponseDto;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.repository.UserRepository;
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

        //Todo
        // 비밀번호 확인하고 삭제하고 싶은데, delete는 RequestBody를 안받고 query param으로 해결한다고 한다.

        userRepository.delete(findUser);

    }
}
