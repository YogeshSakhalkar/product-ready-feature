package com.example.prod_ready_feature.service;

import com.example.prod_ready_feature.dto.PostDto;
import com.example.prod_ready_feature.entity.PostEntity;
import com.example.prod_ready_feature.exception.ResourceNotFoundException;
import com.example.prod_ready_feature.interfaces.PostService;
import com.example.prod_ready_feature.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<PostDto> getAllPost() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity,PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto inputPostDto) {
        System.out.println(inputPostDto);
        PostEntity postEntity =
                modelMapper.map(inputPostDto, PostEntity.class);

        PostEntity savedEntity = postRepository.save(postEntity);

        return modelMapper.map(savedEntity, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("post not found with id"+postId));
        return modelMapper.map(postEntity, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto inputpostDto, Long inputId) {
        PostEntity olderPost = postRepository.findById(inputId).orElseThrow
                (()->new ResourceNotFoundException("post not found with id"+inputId));
        inputpostDto.setId(inputId);
        modelMapper.map(inputpostDto, olderPost);
        PostEntity savedPostEntity = postRepository.save(olderPost);
        return modelMapper.map(savedPostEntity, PostDto.class);
    }
}
