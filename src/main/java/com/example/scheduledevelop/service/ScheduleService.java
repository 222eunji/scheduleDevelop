package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.Schedule.CreateScheduleRequestDto;
import com.example.scheduledevelop.dto.Schedule.CreateScheduleResponseDto;
import com.example.scheduledevelop.dto.Schedule.ScheduleResponseDto;
import com.example.scheduledevelop.dto.Schedule.UpdateScheduleRequestDto;
import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.repository.ScheduleRepository;
import com.example.scheduledevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public CreateScheduleResponseDto createSchedule(CreateScheduleRequestDto requestDto) {

        User findUser = userRepository.findUserByIdOrElseThrow(requestDto.getUserId());

        Schedule schedule = new Schedule(requestDto,findUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponseDto(savedSchedule.getScheduleId(), savedSchedule.getTitle(), savedSchedule.getContent());
    }

    public List<ScheduleResponseDto> findAll() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    @Transactional
    public void updateContent(Long scheduleId, UpdateScheduleRequestDto requestDto) {

        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        findSchedule.updateContent(requestDto.getContent());
    }


    public void deleteSchedule(Long scheduleId) {

        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        scheduleRepository.delete(findSchedule);

    }
}
