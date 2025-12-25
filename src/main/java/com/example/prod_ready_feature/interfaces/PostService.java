package com.example.prod_ready_feature.interfaces;

import com.example.prod_ready_feature.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    List<PostDto> getAllPost();


    PostDto createNewPost(PostDto postDto);

    PostDto getPostById(Long postId);

    PostDto updatePost(PostDto postDto,Long inputId);
}
