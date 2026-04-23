package com.scheduleappdevelopment.user.entity;

import com.scheduleappdevelopment.common.entity.BaseEntity;
import com.scheduleappdevelopment.user.dto.UserUpdateRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // 모든 field에 대한 Getter 자동 생성
@Entity // JPA가 관리하는 Entity 클래스로 정의 (DB table과 Mapping)
@Table(name = "users") // 실제 DB 테이블 이름을 "users"로 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 규정상 기본 생성자 필수, 무분별한 객체 생성 방지를 위해 PROTECTED 설정
public class User extends BaseEntity {
    @Id // 테이블의 기본키(PK) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 전략을 DB의 AUTO_INCREMENT에 위임
    private Long id;

    @Column(nullable = false, length = 20) // NOT NULL 제약조건 설정, 길이 20자 제한
    private String name;

    @Column(nullable = false, unique = true) // NOT NULL 제약조건 설정, 이메일 중복 방지
    private String email;

    @Column(nullable = false) // NOT NULL 제약조건 설정
    private String password;

    /**
     * 빌더 패턴을 이용한 생성자
     * 생성 시점에 필요한 데이터(name, email)를 명확하게 주입
     */
    @Builder
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Service Layer에서 사용할 유저 수정 메서드
     * @param requestDto 수정할 이름(name)과 이메일(email)과 비밀번호(password)가 담긴 DTO
     */
    public void update(UserUpdateRequestDto requestDto) {
        this.name = requestDto.getName();
        this.email = requestDto.getEmail();
        if (requestDto.getPassword() != null) {
            this.password = requestDto.getPassword();
        }
    }
}

