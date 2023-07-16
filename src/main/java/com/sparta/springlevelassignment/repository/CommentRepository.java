package com.sparta.springlevelassignment.repository;

import com.sparta.springlevelassignment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Comment, Long> {

}
