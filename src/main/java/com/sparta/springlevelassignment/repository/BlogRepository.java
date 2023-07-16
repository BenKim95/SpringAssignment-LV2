package com.sparta.springlevelassignment.repository;

import com.sparta.springlevelassignment.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository <Blog, Long> {
    List<Blog> findAllByOrderByCreatedAtDesc(); // 블로그 글 전체 조회할때 위에서 부터 작성한 순서대로 출력 (오름차순), Desc할 경우 반대로
}
