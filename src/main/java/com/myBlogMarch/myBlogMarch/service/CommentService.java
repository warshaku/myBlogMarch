package com.myBlogMarch.myBlogMarch.service;

import com.myBlogMarch.myBlogMarch.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,long postId);

    void deleteComment(long id);

    CommentDto updateComment(long id, CommentDto commentDto, long postId);
}

