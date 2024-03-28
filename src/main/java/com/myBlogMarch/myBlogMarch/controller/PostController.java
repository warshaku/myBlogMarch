package com.myBlogMarch.myBlogMarch.controller;

import com.myBlogMarch.myBlogMarch.payload.PostDto;
import com.myBlogMarch.myBlogMarch.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/posts")
public class PostController {

   private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
@PreAuthorize("hasRole('ADMIN')")//only admin can create post user can not create post
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
    //http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=title
    //http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=title&sortDir=desc
    @GetMapping
    public List<PostDto> getAllPosts(
            @RequestParam(name="pageNo",required = false,defaultValue ="0")int pageNo,
            @RequestParam(name="pageSize",required = false,defaultValue = "3")int pageSize,
            @RequestParam(name="sortBy",required = false,defaultValue = "id")String sortBy,
            @RequestParam(name="sortDir",required = false,defaultValue = "id")String sortDir
    ){
      List<PostDto>postDto=  postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
      return postDto;
    }
}
