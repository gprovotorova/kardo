package com.kardoaward.kardo.user;

import com.kardoaward.kardo.client.BaseClient;
import com.kardoaward.kardo.comment.NewCommentDto;
import com.kardoaward.kardo.direction.DirectionDto;
import com.kardoaward.kardo.post.dto.NewPostDto;
import com.kardoaward.kardo.post.dto.PostDto;
import com.kardoaward.kardo.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;
import java.util.Set;

@Service
public class UserClient extends BaseClient {
    private static final String API_PREFIX = "/users";

    @Autowired
    public UserClient(@Value("${kardo-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity updateUser(Long userId, UserDto userDto) {
        return patch("/" + userId, userDto);
    }

    public ResponseEntity createComment(Long userId, Long eventId, NewCommentDto commentDto) {
        return post("/" + userId + "/events/" + eventId, commentDto);
    }

    public ResponseEntity updateComment(Long userId, Long eventId, Long commentId, NewCommentDto commentDto) {
        return patch("/" + userId + "/events/" + eventId + "/comments/" + commentId, commentDto);
    }

    public ResponseEntity deleteComment(Long userId, Long commentId) {
        return delete("/" + userId + "/comments/" + commentId);
    }

    public ResponseEntity createPost(Long userId, NewPostDto postDto) {
        return post("/" + userId + "/posts/", postDto);
    }

    public ResponseEntity updatePost(Long userId, Long postId, PostDto postDto) {
        return patch("/" + userId + "/posts/" + postId, postDto);
    }

    public ResponseEntity deletePost(Long userId, Long postId) {
        return delete("/" + userId + "/posts/" + postId);
    }

    public ResponseEntity getCommentById(Long commentId, Long userId) {
        return get("/" + userId + "/comments/" + commentId);
    }

    public ResponseEntity getAllByPostIdAndUserId(Long userId,
                                                   Long postId,
                                                   Integer from,
                                                   Integer size) {
        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size
        );
        return get("/" + userId + "/posts/" + postId + "/comments?from={from}&size={size}", parameters);
    }

    public ResponseEntity getAllByUserId(Long userId,
                                         Integer from,
                                         Integer size) {
        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size
        );
        return get("/" + userId + "/comments?from={from}&size={size}", parameters);
    }

    public ResponseEntity addNewRequestToEventByUser(Long userId, Long eventId) {
        return post("/" + userId + "/events/" + eventId + "/request/");
    }

    public ResponseEntity addNewRequestToCompetitionByUser(Long userId, Long compId, Set<DirectionDto> directions) {
        return post("/" + userId + "/competition/" + compId + "/request/", directions);
    }

    public ResponseEntity getEventsRequestsByUserId(Long userId, Integer from, Integer size) {
        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size
        );
        return get("/" + userId + "/events/requests", parameters);
    }

    public ResponseEntity getCompetitionsRequestsByUserId(Long userId, Integer from, Integer size) {
        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size
        );
        return get("/" + userId + "/competition/requests", parameters);
    }

    public ResponseEntity getParticipantsByCompetitionIdAndUserId(Long userId, Long compId, Integer from, Integer size) {
        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size
        );
        return get("/" + userId + "/competition/" + compId + "/participants", parameters);
    }

    public ResponseEntity evaluateParticipant(Long userId, Long compId, Long partId, int[] points) {
        return patch("/" + userId + "/competition/" + compId + "/participants/" + partId, points);
    }

    public ResponseEntity addLike(Long userId, Long postId, String type) {
        return post("/" + userId + "/posts/" + postId + "like", type);
    }

    public ResponseEntity addDislike(Long userId, Long postId, String type) {
        return post("/" + userId + "/posts/" + postId + "/dislike", type);
    }
}
