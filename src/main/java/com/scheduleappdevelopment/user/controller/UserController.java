package com.scheduleappdevelopment.user.controller;

import com.scheduleappdevelopment.user.dto.*;
import com.scheduleappdevelopment.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // @Controller + @ResponseBody Annotation
@RequestMapping("/users") // 공통 Mapping Annotation
@RequiredArgsConstructor // final field를 생성해주는 Annotation (userService)
public class UserController {

    private final UserService userService;

    /**
     * 유저 생성 API
     * POST /users
     * @return 생성된 유저 데이터, 201 Created
     */
    @PostMapping
    public ResponseEntity<UserCreateResponseDto> createUserAPI(@Valid @RequestBody UserCreateRequestDto requestDto){
        UserCreateResponseDto responseDto = userService.createUser(requestDto);
        ResponseEntity<UserCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        return response;
    }

    /** 유저 전체 조회 API
     * GET /users
     * @return 전체 유저 목록 데이터, 200 OK
     */
    @GetMapping
    public ResponseEntity<UserListResponseDto> getUserListAPI() {
        UserListResponseDto responseDto = userService.getUserList();
        ResponseEntity<UserListResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * 유저 단건 조회 API
     * GET /users/{userId}
     * @param userId 조회할 유저의 식별자
     * @return 해당 유저 데이터, 200 OK
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserListResponseDto.UserDto> getUserAPI(@PathVariable Long userId) {
        UserListResponseDto.UserDto responseDto = userService.getUser(userId);
        ResponseEntity<UserListResponseDto.UserDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * 유저 수정 API
     * PUT /users/{userId}
     * @param userId 수정할 유저의 식별자
     * @param requestDto 수정할 데이터 내용
     * @return 수정된 유저 데이터, 200 OK
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserUpdateResponseDto> updateUserAPI(
            @PathVariable Long userId, @RequestBody UserUpdateRequestDto requestDto
    ) {
        UserUpdateResponseDto responseDto = userService.updateUser(userId, requestDto);
        ResponseEntity<UserUpdateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * 유저 삭제 API
     * DELETE /users/{userId}
     * @param userId 삭제할 유저의 식별자
     * @return 204 No Content
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserAPI(@PathVariable Long userId) {
        userService.deleteUser(userId);
        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return response;
    }
}
