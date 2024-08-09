package com.kardoaward.kardo.user.service;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.comment.dto.NewCommentDto;
import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.direction.Direction;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.participant.dto.CompetitionParticipantDto;
import com.kardoaward.kardo.participant.dto.EvaluatingParticipantDto;
import com.kardoaward.kardo.participant.dto.EventParticipantDto;
import com.kardoaward.kardo.post.dto.NewPostDto;
import com.kardoaward.kardo.post.dto.PostDto;
import com.kardoaward.kardo.user.dto.NewUserDto;
import com.kardoaward.kardo.user.dto.UserDto;
import com.kardoaward.kardo.user.dto.UserEntrance;
import com.kardoaward.kardo.user.model.User;
import org.springframework.data.domain.Pageable;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(Long userId, NewUserDto userDto);

    void deleteUser(Long userId);

    CommentDto createComment(Long userId, Long eventId, NewCommentDto commentDto);

    CommentDto updateComment(Long userId, Long eventId, Long commentId, NewCommentDto commentDto);

    void deleteComment(Long userId, Long commentId);

    List<CommentDto> getAllByPostIdAndUserId(Long eventId, Long userId, Pageable page);

    List<CommentDto> getAllByUserId(Long userId, Pageable page);

    CommentDto getByCommentId(Long commentId, Long userId);

    EventParticipantDto addNewRequestToEventByUser(Long userId, Long eventId);

    CompetitionParticipantDto addNewRequestToCompetitionByUser(Long userId, Long compId, Set<Direction> directions);

    List<EventDto> getEventsRequestsByUserId(Long userId, Pageable page);

    List<CompetitionDto> getCompetitionsRequestsByUserId(Long userId, Pageable page);

    List<CompetitionParticipantDto> getParticipantsByCompetitionIdAndUserId(Long userId, Long compId, Pageable page);

    EvaluatingParticipantDto evaluateParticipant(Long userId, Long compId, Long partId, int[] points);

    PostDto createPost(Long userId, Long fileId, DirectionType direction, NewPostDto postDto);

    PostDto updatePost(Long userId, Long postId, PostDto postDto);

    void deletePost(Long userId, Long postId);

    PostDto addLikeOrDislike(Long userId, Long postId, String type);

    UserDto registration(NewUserDto userDto);

    UserDto login(UserEntrance userEntrance);

    UserDto getUserById(Long userId);
}
