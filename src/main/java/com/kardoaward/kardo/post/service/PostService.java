package com.kardoaward.kardo.post.service;

import com.kardoaward.kardo.post.dto.PostDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    PostDto getPostById(Long streamId);

    List<PostDto> getAllPosts(Pageable page);
}
