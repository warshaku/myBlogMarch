package com.myBlogMarch.myBlogMarch.controller;

import com.myBlogMarch.myBlogMarch.payload.PostDto;
import com.myBlogMarch.myBlogMarch.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

   private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/posts/particular?id=1
    @GetMapping("/particular")
    public ResponseEntity<PostDto>getPostById(@RequestParam long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
    //http://localhost:8080/api/posts?pageNo=0&pageSize=3
    @GetMapping
    public List<PostDto> getAllPosts(
            @RequestParam(name="pageNo",required = false)int pageNo,
            @RequestParam(name="pageSize",required = false)int pageSize
    ){
      List<PostDto>postDto=  postService.getAllPosts(pageNo,pageSize);
      return postDto;
    }
}
