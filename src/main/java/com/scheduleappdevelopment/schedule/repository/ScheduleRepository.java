package com.scheduleappdevelopment.schedule.repository;

import com.scheduleappdevelopment.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
