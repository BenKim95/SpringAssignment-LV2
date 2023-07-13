package com.sparta.springlevelassignment.repository;

import com.sparta.springlevelassignment.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository <Blog, Long> {

}
