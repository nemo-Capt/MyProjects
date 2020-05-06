package com.project.repository;

import com.project.entity.UserCredits;
import com.project.entity.UserDeposits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDepositsRepository extends JpaRepository<UserDeposits, Long> {
}
