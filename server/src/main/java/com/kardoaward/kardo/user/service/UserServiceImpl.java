package com.kardoaward.kardo.user.service;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.comment.dto.NewCommentDto;
import com.kardoaward.kardo.comment.mapper.CommentMapper;
import com.kardoaward.kardo.comment.model.Comment;
import com.kardoaward.kardo.comment.repository.CommentRepository;
import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.competition.model.Competition;
import com.kardoaward.kardo.competition.repository.CompetitionRepository;
import com.kardoaward.kardo.direction.Direction;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.enums.UserType;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.event.model.Event;
import com.kardoaward.kardo.event.repository.EventRepository;
import com.kardoaward.kardo.exception.ConflictDataException;
import com.kardoaward.kardo.exception.ObjectExistException;
import com.kardoaward.kardo.exception.ObjectNotFoundException;
import com.kardoaward.kardo.file.dao.FileDAO;
import com.kardoaward.kardo.file.model.FileInfo;
import com.kardoaward.kardo.likes.Likes;
import com.kardoaward.kardo.likes.LikesMapper;
import com.kardoaward.kardo.likes.LikesRepository;
import com.kardoaward.kardo.participant.dto.CompetitionParticipantDto;
import com.kardoaward.kardo.participant.dto.EvaluatingParticipantDto;
import com.kardoaward.kardo.participant.dto.EventParticipantDto;
import com.kardoaward.kardo.participant.mapper.ParticipantMapper;
import com.kardoaward.kardo.participant.model.CompetitionParticipant;
import com.kardoaward.kardo.participant.model.EvaluatingParticipant;
import com.kardoaward.kardo.participant.model.EventParticipant;
import com.kardoaward.kardo.participant.repository.CompetitionParticipantRepository;
import com.kardoaward.kardo.participant.repository.EvaluatingParticipantRepository;
import com.kardoaward.kardo.participant.repository.EventParticipantRepository;
import com.kardoaward.kardo.post.dto.NewPostDto;
import com.kardoaward.kardo.post.dto.PostDto;
import com.kardoaward.kardo.post.mapper.PostMapper;
import com.kardoaward.kardo.post.model.Post;
import com.kardoaward.kardo.post.repository.PostRepository;
import com.kardoaward.kardo.user.dto.UserDto;
import com.kardoaward.kardo.user.dto.UserEntrance;
import com.kardoaward.kardo.user.model.User;
import com.kardoaward.kardo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final PostRepository postRepository;
    private final EventParticipantRepository eventParticipantRepository;
    private final CompetitionParticipantRepository competitionParticipantRepository;
    private final CompetitionRepository competitionRepository;
    private final EvaluatingParticipantRepository evaluatingParticipantRepository;
    private final LikesRepository likesRepository;
    private final FileDAO fileDAO;
    private ModelMapper mapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ObjectExistException("Пользователь с таким e-mail уже существует.");
        }
        return mapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        userRepository.existsById(userId);
        User user = mapper.map(userDto, User.class);
        return mapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .ifPresent(user -> userRepository.deleteById(userId));
    }

    @Override
    public CommentDto createComment(Long userId, Long postId, NewCommentDto newCommentDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ObjectNotFoundException("Пост с id = " + postId + " не найден."));

        LocalDateTime time = LocalDateTime.now();

        Comment comment = CommentMapper.toComment(newCommentDto, post, user, time);

        return CommentMapper.toCommentDto(commentRepository.save(comment), post, user);
    }

    @Override
    public CommentDto updateComment(Long userId, Long postId, Long commentId, NewCommentDto commentDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ObjectNotFoundException("Пост с id = " + postId + " не найден."));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ObjectNotFoundException("Комментарий с id = " + commentId + " не найден."));

        comment.setText(commentDto.getText());
        comment.setUpdatedDate(LocalDateTime.now());

        return CommentMapper.toCommentDto(commentRepository.save(comment), post, user);
    }

    @Override
    public void deleteComment(Long userId, Long commentId) {
        userRepository.existsById(userId);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ObjectNotFoundException("Комментарий с id = " + commentId + " не найден."));

        Long authorId = comment.getAuthor().getId();
        if (!authorId.equals(userId)) {
            throw new ConflictDataException("Комментарий может удалить только автор комментария.");
        }
        commentRepository.deleteById(commentId);
    }


    @Override
    public PostDto createPost(Long userId, Long fileId, DirectionType direction, NewPostDto newPostDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));
        FileInfo file = fileDAO.findById(fileId);
        LocalDateTime time = LocalDateTime.now();
        Post post = PostMapper.toPost(newPostDto, file, user, time, direction);

        return mapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public PostDto updatePost(Long userId, Long postId, PostDto postDto) {
        userRepository.existsById(userId);
        Post savedPost = postRepository.findById(postId)
                .orElseThrow(() -> new ObjectNotFoundException("Пост с id = " + userId + " не найден."));

        Long authorId = savedPost.getAuthor().getId();
        if (!authorId.equals(userId)) {
            throw new ConflictDataException("Пост может изменить только автор.");
        }

        Long likes = likesRepository.getLikesOrDislikes(postId, "like");
        Long dislikes = likesRepository.getLikesOrDislikes(postId, "dislike");

        Post post = mapper.map(postDto, Post.class);
        post.setLikes(likes);
        post.setDislikes(dislikes);
        post.setUpdatedDate(LocalDateTime.now());
        return mapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public void deletePost(Long userId, Long postId) {
        userRepository.existsById(userId);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ObjectNotFoundException("Пост  с id = " + postId + " не найден."));

        Long authorId = post.getAuthor().getId();
        if (!authorId.equals(userId)) {
            throw new ConflictDataException("Пост  может удалить только автор комментария.");
        }
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto addLikeOrDislike(Long userId, Long postId, String type) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ObjectNotFoundException("Пост  с id = " + postId + " не найден."));

        Likes likes = LikesMapper.toLikesOrDislikes(user, post, type);
        likesRepository.save(likes);
        return mapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getAllByPostIdAndUserId(Long postId, Long userId, Pageable page) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ObjectNotFoundException("Пост  с id = " + postId + " не найден."));

        return commentRepository.findAllByPostIdAndAuthorId(postId, userId, page)
                .getContent()
                .stream()
                .map(comment -> CommentMapper.toCommentDto((Comment) comment, post, user))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto getByCommentId(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ObjectNotFoundException("Комментарий с id = " + commentId + " не найден."));
        return CommentMapper.toCommentDto(comment, comment.getPost(), comment.getAuthor());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getAllByUserId(Long userId, Pageable page) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));

        return commentRepository.findAllByAuthorId(userId, page)
                .getContent()
                .stream()
                .map(comment -> CommentMapper.toCommentDto(comment, comment.getPost(), user))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EventParticipantDto addNewRequestToEventByUser(Long userId, Long eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ObjectNotFoundException("Мероприятие с id = " + eventId + " не найдено."));

        EventParticipant eventParticipant = ParticipantMapper.toEventParticipant(user, event);
        return mapper.map(eventParticipantRepository.save(eventParticipant), EventParticipantDto.class);
    }

    @Override
    @Transactional
    public CompetitionParticipantDto addNewRequestToCompetitionByUser(Long userId, Long compId,
                                                                      Set<Direction> directions) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));
        Competition competition = competitionRepository.findById(compId)
                .orElseThrow(() -> new ObjectNotFoundException("Конкурс с id = " + compId + " не найден."));

        CompetitionParticipant competitionParticipant =
                ParticipantMapper.toCompetitionParticipant(user, competition, directions);
        return mapper.map(competitionParticipantRepository.save(competitionParticipant),
                CompetitionParticipantDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDto> getEventsRequestsByUserId(Long userId, Pageable page) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));

        return eventParticipantRepository.findAllEventsByUserId(userId, page)
                .getContent()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompetitionDto> getCompetitionsRequestsByUserId(Long userId, Pageable page) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));

        return competitionParticipantRepository.findAllCompetitionsByUserId(userId, page)
                .getContent()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompetitionParticipantDto> getParticipantsByCompetitionIdAndUserId(Long userId, Long compId, Pageable page) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));
        if (!user.getType().equals(UserType.EXPERT)) {
            throw new ObjectNotFoundException("Пользователь с id = " + userId + " не является судьей.");
        }

        return competitionParticipantRepository.findAllByUserIdAndCompetitionId(userId, compId, page)
                .getContent()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EvaluatingParticipantDto evaluateParticipant(Long userId, Long compId, Long partId, int[] points) {
        User expert = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));
        if (!expert.getType().equals(UserType.EXPERT)) {
            throw new ObjectNotFoundException("Пользователь с id = " + userId + " не является судьей.");
        }
        Competition competition = competitionRepository.findById(compId)
                .orElseThrow(() -> new ObjectNotFoundException("Конкурс с id = " + compId + " не найден."));
        CompetitionParticipant participant = competitionParticipantRepository.findById(partId)
                .orElseThrow(() -> new ObjectNotFoundException("Участник с id = " + partId + " не найден."));

        Set<Direction> competitionDirections = competition.getDirections();
        Set<Direction> participantDirections = participant.getDirections();
        if (!competitionDirections.contains(participantDirections)) {
            throw new ObjectNotFoundException("Направления конкурса не совпадают с навправлениями участника");
        }

        OptionalDouble average = Arrays.stream(points).average();

        EvaluatingParticipant evaluatingParticipant =
                ParticipantMapper.toEvaluatingParticipant(competition, participant, expert, average);
        return mapper.map(evaluatingParticipantRepository.save(evaluatingParticipant),
                EvaluatingParticipantDto.class);
    }

    @Override
    public String registration(UserDto userDto){
        User user = mapper.map(userDto, User.class);
        if (userRepository.existsUserByNameAndSurname(user.getName(), user.getSurname())) {
            throw new ConflictDataException("Такой пользователь уже существует");
        }
        userRepository.save(user);
        return user.getId().toString();
    }

    @Override
    public String login(UserEntrance userEntrance){
        User user = userRepository.findByEmail(userEntrance.getEmail());
        if(!user.getPassword().equals(userEntrance.getPassword())){
            throw new ConflictDataException("Неверный логин или пароль");
        }
        return user.getId().toString();
    }
}
