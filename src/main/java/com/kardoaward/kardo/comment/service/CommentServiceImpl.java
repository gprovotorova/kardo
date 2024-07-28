package com.kardoaward.kardo.comment.service;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.comment.dto.NewCommentDto;
import com.kardoaward.kardo.comment.mapper.CommentMapper;
import com.kardoaward.kardo.comment.model.Comment;
import com.kardoaward.kardo.comment.repository.CommentRepository;
import com.kardoaward.kardo.event.model.Event;
import com.kardoaward.kardo.event.repository.EventRepository;
import com.kardoaward.kardo.exception.ConflictDataException;
import com.kardoaward.kardo.exception.ObjectNotFoundException;
import com.kardoaward.kardo.exception.ObjectValidationException;
import com.kardoaward.kardo.foto.repository.PhotoRepository;
import com.kardoaward.kardo.photo.Photo;
import com.kardoaward.kardo.user.model.User;
import com.kardoaward.kardo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.kardoaward.kardo.common.Constants.MAX_DATE;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    private final PhotoRepository photoRepository;

    @Override
    @Transactional
    public CommentDto createComment(Long userId, Long photoId, NewCommentDto newCommentDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));
        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new ObjectNotFoundException("Событие с id = " + photoId + " не найдено."));

//        if (!event.getState().equals(EventState.PUBLISHED)) {
//            throw new ConflictDataException("Комментарий нельзя оставить у неопубликованного события.");
//        }

        LocalDateTime time = LocalDateTime.now();

        Comment comment = CommentMapper.toComment(newCommentDto, photo, user,time);

        return CommentMapper.toCommentDto(commentRepository.save(comment), photo, user);
    }

    @Override
    @Transactional
    public CommentDto updateComment(Long userId, Long photoId, Long commentId, NewCommentDto commentDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));
        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new ObjectNotFoundException("Событие с id = " + photoId + " не найдено."));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ObjectNotFoundException("Комментарий с id = " + commentId + " не найден."));

        comment.setText(commentDto.getText());
        comment.setUpdated(LocalDateTime.now());

        return CommentMapper.toCommentDto(commentRepository.save(comment), photo, user);
    }

    @Override
    @Transactional
    public void deleteCommentByUser(Long userId, Long commentId) {
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
    @Transactional(readOnly = true)
    public List<CommentDto> getAllByEventIdAndUserId(Long photoId, Long userId, Pageable page) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));
        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new ObjectNotFoundException("Событие с id = " + photoId + " не найдено."));

        return commentRepository.findAllByEventIdAndAuthorId(photoId, userId, page)
                .getContent()
                .stream()
                .map(comment -> CommentMapper.toCommentDto(comment, photo, user))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getAllComments(Pageable page) {
        return commentRepository.findAll(page)
                .getContent()
                .stream()
                .map(comment -> CommentMapper.toCommentDto(comment, comment.getPhoto(), comment.getAuthor()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getAllByUserId(Long userId, Pageable page) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Пользователь с id = " + userId + " не найден."));

        return commentRepository.findAllByAuthorId(userId, page)
                .getContent()
                .stream()
                .map(comment -> CommentMapper.toCommentDto(comment, comment.getPhoto(), user))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto getByCommentId(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ObjectNotFoundException("Комментарий с id = " + commentId + " не найден."));
        return CommentMapper.toCommentDto(comment, comment.getPhoto(), comment.getAuthor());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getByEventId(Long eventId, Pageable page) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ObjectNotFoundException("Событие с id = " + eventId + " не найдено."));

        return commentRepository.findAllByEventId(eventId, page)
                .getContent()
                .stream()
                .map(comment -> CommentMapper.toCommentDto(comment, comment.getPhoto(), comment.getAuthor()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsWithFilters(LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable page) {
        if (rangeStart != null && rangeEnd != null && rangeStart.isAfter(rangeEnd)) {
            throw new ObjectValidationException("Дата начала сортировки не может быть позже даты конца.");
        }

        if (rangeStart == null) rangeStart = LocalDateTime.now();
        if (rangeEnd == null) rangeEnd = MAX_DATE;

        List<CommentDto> comments = commentRepository.getCommentsWithFilters(rangeStart, rangeEnd, page)
                .getContent().stream()
                .map(comment -> CommentMapper.toCommentDto(comment, comment.getPhoto(), comment.getAuthor()))
                .collect(Collectors.toList());
        return comments;
    }
}
