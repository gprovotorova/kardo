package com.kardoaward.kardo.post.service;

import com.kardoaward.kardo.enums.UserType;
import com.kardoaward.kardo.exception.ConflictDataException;
import com.kardoaward.kardo.exception.ObjectNotFoundException;
import com.kardoaward.kardo.exception.StorageFileNotFoundException;
import com.kardoaward.kardo.post.dto.PostDto;
import com.kardoaward.kardo.post.model.Post;
import com.kardoaward.kardo.post.repository.PostRepository;
import com.kardoaward.kardo.user.model.User;
import com.kardoaward.kardo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private ModelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public PostDto getPostById(Long userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new StorageFileNotFoundException("Такого пользователя не существует"));
        if (user.getType().equals(UserType.WATCHER)){
            throw new ConflictDataException("Нет доступа");
        }
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ObjectNotFoundException("Пост с id = " + postId + " не найден."));
        return mapper.map(post, PostDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getAllPosts(Pageable page) {
        return postRepository.findAll(page)
                .getContent()
                .stream()
                .map(stream -> mapper.map(stream, PostDto.class))
                .collect(Collectors.toList());
    }
}
