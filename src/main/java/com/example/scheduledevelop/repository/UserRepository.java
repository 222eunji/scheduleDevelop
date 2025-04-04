package com.example.scheduledevelop.repository;

import com.example.scheduledevelop.dto.User.LoginRequestDto;
import com.example.scheduledevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    default User findUserByIdOrElseThrow(Long id){

        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id가 존재하지 않습니다."));

    }

    default User findUserByEmailOrElseThrow(LoginRequestDto requestDto){
        return findByEmail(requestDto.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "이메일이 존재하지 않습니다."));
    }

}
