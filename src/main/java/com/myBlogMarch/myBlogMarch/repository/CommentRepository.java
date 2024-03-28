package com.myBlogMarch.myBlogMarch.repository;

import com.myBlogMarch.myBlogMarch.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
