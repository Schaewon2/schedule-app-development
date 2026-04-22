package com.scheduleappdevelopment.user.dto;

import com.scheduleappdevelopment.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // JSON 데이터를 DTO 객체로 변환할 때(Jackson 라이브러리) 기본 생성자가 반드시 필요
@Getter // 데이터를 꺼내기 위한 Getter
public class UserCreateRequestDto {

    private String name;
    private String email;
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
