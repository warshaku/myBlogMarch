package com.myBlogMarch.myBlogMarch.service.impl;

import com.myBlogMarch.myBlogMarch.entity.Post;
import com.myBlogMarch.myBlogMarch.exception.ResourceNotFoundException;
import com.myBlogMarch.myBlogMarch.payload.PostDto;
import com.myBlogMarch.myBlogMarch.repository.PostRepository;
import com.myBlogMarch.myBlogMarch.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

private PostRepository postRepository;

private ModelMapper modelmapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelmapper) {
        this.postRepository = postRepository;
        this.modelmapper = modelmapper;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
     Post post= mapToEntity(postDto);
       Post savedPost= postRepository.save(post);
        PostDto dto =  mapToDto(savedPost);
        return dto ;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post Not found with id:"+id)
        );
        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
      Sort sort = (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        Page<Post> pagePost = postRepository.findAll(pageable);
        List<Post> posts = pagePost.getContent();
        List<PostDto> dtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        return dtos;
    }

   PostDto mapToDto(Post post){
       PostDto dto = modelmapper.map(post, PostDto.class);
       return dto;
    }
    Post mapToEntity(PostDto postDto) {
        Post post = modelmapper.map(postDto, Post.class);
        return post;

    }
}
