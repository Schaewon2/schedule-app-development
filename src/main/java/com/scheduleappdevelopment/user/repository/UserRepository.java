package com.scheduleappdevelopment.user.repository;

import com.scheduleappdevelopment.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
