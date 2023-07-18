package com.sparta.springlevelassignment.repository;

import com.sparta.springlevelassignment.entity.PasswordHistory;
import com.sparta.springlevelassignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordRepository extends JpaRepository <PasswordHistory, Long> {
   List<PasswordHistory> findTop3ByUserOrderByModifiedAtDesc(User user);
}
