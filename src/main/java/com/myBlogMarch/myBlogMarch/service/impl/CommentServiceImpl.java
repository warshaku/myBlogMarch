package com.myBlogMarch.myBlogMarch.service.impl;

import com.myBlogMarch.myBlogMarch.entity.Comment;
import com.myBlogMarch.myBlogMarch.entity.Post;
import com.myBlogMarch.myBlogMarch.exception.ResourceNotFoundException;
import com.myBlogMarch.myBlogMarch.payload.CommentDto;
import com.myBlogMarch.myBlogMarch.repository.CommentRepository;
import com.myBlogMarch.myBlogMarch.repository.PostRepository;
import com.myBlogMarch.myBlogMarch.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;


    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id:" + postId)
        );
        Comment comment = new Comment();
        comment.setEmail(commentDto.getEmail());
        comment.setText(commentDto.getText());
        comment.setPost(post);
        Comment saveComment = commentRepository.save(comment);
        CommentDto dto = new CommentDto();
        dto.setId(saveComment.getId());
        dto.setEmail(saveComment.getEmail());
        dto.setText(saveComment.getText());
        return dto;
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {
Post post=postRepository.findById(postId).orElseThrow(
        ()->new ResourceNotFoundException("post not found with id:"+id)
);
            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Comment not found with id:" + id)
            );
            Comment c = mapToEntity(commentDto);
            c.setId(comment.getId());
            c.setPost(post);
            Comment savedComment = commentRepository.save(c);

            return mapToDto(savedComment);
        }
        CommentDto mapToDto(Comment comment) {
            CommentDto dto = modelMapper.map(comment, CommentDto.class);
            return dto;
        }
        Comment mapToEntity(CommentDto commentDto) {
            Comment comment = modelMapper.map(commentDto, Comment.class);
            return comment;
        }
    }
    

