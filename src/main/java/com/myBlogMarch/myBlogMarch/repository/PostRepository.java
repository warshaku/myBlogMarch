package com.myBlogMarch.myBlogMarch.repository;

import com.myBlogMarch.myBlogMarch.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {


}
