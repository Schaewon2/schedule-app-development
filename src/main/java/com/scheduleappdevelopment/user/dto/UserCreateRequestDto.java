package com.scheduleappdevelopment.user.dto;

import com.scheduleappdevelopment.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // JSON 데이터를 DTO 객체로 변환할 때(Jackson 라이브러리) 기본 생성자가 반드시 필요
@Getter // 데이터를 꺼내기 위한 Getter
public class UserCreateRequestDto {

    @NotBlank(message = "이름은 필수입니다.") // 값이 무조건 있어야 하고, 공백만 있어도 안된다.
    private String name;
    @NotBlank(message = "이메일은 필수입니다.") // 값이 무조건 있어야 하고, 공백만 있어도 안된다.
    @Email(message = "이메일 형식이 아닙니다.") // 이메일 형식 지키기
    private String email;
    @Size(min = 8, message = "비밀번호는 최소 8글자 이상이어야 합니다.") // 최소 8글자 이상, 메시지
    @NotBlank(message = "비밀번호는 필수 입력값입니다.") // 값이 무조건 있어야 하고, 공백만 있어도 안된다.
    private String password;

    @Builder // 빌더 패턴 : 객체 생성 시 field 명을 명시하여 가독성을 높인다.
    public UserCreateRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * 인스턴스 메서드 : DTO가 가진 데이터를 기반으로 Entity 객체를 생성한다.
     */
    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
