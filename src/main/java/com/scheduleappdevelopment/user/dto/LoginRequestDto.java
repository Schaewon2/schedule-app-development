package com.scheduleappdevelopment.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // JSON 데이터를 DTO 객체로 변환할 때(Jackson 라이브러리) 기본 생성자가 반드시 필요
@Getter // 데이터를 꺼내기 위한 Getter
public class LoginRequestDto {
    @NotBlank(message = "이메일은 필수입니다.")
    @Email
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
