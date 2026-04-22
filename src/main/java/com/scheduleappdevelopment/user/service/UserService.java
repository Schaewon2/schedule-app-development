package com.scheduleappdevelopment.user.service;

import com.scheduleappdevelopment.user.dto.*;
import com.scheduleappdevelopment.user.entity.User;
import com.scheduleappdevelopment.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 'Service'를 상징하는 Annotation
@RequiredArgsConstructor // final field를 생성해주는 Annotation (userRepository)
public class UserService {

    private final UserRepository userRepository;

    /**
     * 유저 생성 서비스
     * 1. Controller에서 요청 데이터(UserCreateRequestDto) 받아오기
     * 2. 받아온 데이터로 user Entity 만들기
     * 3. userRepository에 user Entity 저장하기
     * 4. 저장된 user Entity (savedUser) 반환받기
     * 5. 응답 데이터(UserCreateResponseDto) 반환하기
     */
    @Transactional
    // 1. Controller에서 요청 데이터(UserCreateRequestDto) 받아오기
    public UserCreateResponseDto createUser(UserCreateRequestDto requestDto) {
        // 2. 받아온 데이터로 user Entity 만들기
        User user = requestDto.toEntity();
        // 3. userRepository에 user Entity 저장하기
        // 4. 저장된 user Entity (savedUser) 반환받기
        User savedUser = userRepository.save(user);
        // 5. 응답 데이터(UserCreateResponseDto) 반환하기
        return UserCreateResponseDto.of(savedUser);
    }

    /**
     * 유저 전체 조회 서비스
     * 1. userRepository에서 만든 모든 User Entity를 조회하며 userList에 담아주기
     * 2. 조회된 Entity List를 응답용 DTO(UserListResponseDto)로 변환하고, 반환하기
     */
    @Transactional(readOnly = true) // 읽기 전용
    public UserListResponseDto getUserList() {
        // 1. userRepository에서 만든 모든 User Entity를 조회하며 userList에 담아주기
        List<User> userList = userRepository.findAll();
        // 2. 조회된 Entity List를 응답용 DTO(UserListResponseDto)로 변환하고, 반환하기
        return UserListResponseDto.of(userList);
    }

    /**
     * 유저 단건 조회 서비스
     * 1. userId를 통해 Repository에서 특정 Entity를 조회한다.
     * 2. 조회 결과가 없을 경우(null) 예외(IllegalArgumentException)를 발생시킨다.
     * 3. 조회된 Entity를 응답용 DTO(UserDto)로 변환하여 반환한다.
     */
    @Transactional(readOnly = true) // 읽기 전용
    // 1. userId를 통해 Repository에서 특정 Entity를 조회한다.
    public UserListResponseDto.UserDto getUser(Long userId) {
        // 2. 조회 결과가 없을 경우(null) 예외(IllegalArgumentException)를 발생시킨다.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        // 3. 조회된 Entity를 응답용 DTO(UserDto)로 변환하여 반환한다.
        return UserListResponseDto.UserDto.of(user);
    }

    /**
     * 유저 수정 서비스
     * 1. Parameter로 받은 userId를 사용하여 수정할 Entity를 조회하기
     * 2. 조회 결과가 없을 경우 예외를 발생시켜 수정 절차 중단하기
     * 3. 받아온 요청 데이터(requestDto)로 Entity의 필드 값 업데이트하기 (Dirty Checking)
     * 4. 수정된 Entity를 응답용 DTO(UserUpdateResponseDto)로 변환하여 반환하기
     */
    @Transactional
    public UserUpdateResponseDto updateUser(Long userId, UserUpdateRequestDto requestDto) {
        // 1. Parameter로 받은 userId를 사용하여 수정할 Entity를 조회하기
        // 2. 조회 결과가 없을 경우 예외를 발생시켜 수정 절차 중단하기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // 3. 받아온 요청 데이터(requestDto)로 Entity의 필드 값 업데이트하기 (Dirty Checking)
        user.update(requestDto);
        // 4. 수정된 Entity를 응답용 DTO(UserUpdateResponseDto)로 변환하여 반환하기
        return UserUpdateResponseDto.of(user);
    }

    /**
     * 유저 삭제 서비스
     * 1. 파라미터로 받은 userId를 사용하여 삭제할 Entity가 존재하는지 확인하기
     * 2. 존재하지 않을 경우 예외를 발생시켜 삭제 절차 중단하기
     * 3. Repository를 통해 해당 ID를 가진 데이터를 DB에서 삭제하기
     */
    @Transactional
    // 1. 파라미터로 받은 userId를 사용하여 삭제할 Entity가 존재하는지 확인하기
    // 2. 존재하지 않을 경우 예외를 발생시켜 삭제 절차 중단하기
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        // 3. Repository를 통해 해당 ID를 가진 데이터를 DB에서 삭제하기
        userRepository.deleteById(userId);
    }
}
