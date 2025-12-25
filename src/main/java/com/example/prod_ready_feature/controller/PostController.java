package com.example.prod_ready_feature.controller;

import com.example.prod_ready_feature.dto.PostDto;
import com.example.prod_ready_feature.service.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

    private final PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPost();
    }

    @PostMapping
    public ResponseEntity<PostDto> createNewPosts(@RequestBody  PostDto postDto){
        PostDto savedPost = postService.createNewPost(postDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @GetMapping(value = "/{postId}")
    public PostDto getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public PostDto updatePost(@RequestBody PostDto inputPost,@PathVariable Long postId){
        return postService.updatePost(inputPost,postId);
    }
}
