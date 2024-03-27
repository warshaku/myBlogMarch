package com.myBlogMarch.myBlogMarch.service;

import com.myBlogMarch.myBlogMarch.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostDto  getPostById(long id);

    List<PostDto> getAllPosts(int pageNo, int pageSize);
}
