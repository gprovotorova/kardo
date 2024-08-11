package com.kardoaward.kardo.websocket;

import com.kardoaward.kardo.admin.service.AdminService;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.post.dto.NewPostDto;
import com.kardoaward.kardo.post.dto.PostDto;
import com.kardoaward.kardo.streams.dto.StreamDto;
import com.kardoaward.kardo.user.controller.UserController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Tag(name="WebSockets")
@Controller
public class LineController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserController userController;

    @Operation(description = "Добавление поста")
    @MessageMapping("/users/{userId}/posts")
    @SendTo("/lines")
    public PostDto addPost(@Payload NewPostDto postDto,
                           @Payload DirectionType direction,
                           @Payload MultipartFile attachment) throws Exception {
        Thread.sleep(1000);
        PostDto saved = userController.createPost(postDto.getAuthor().getId(), postDto, direction, attachment);
        return saved;
    }

    @Operation(description = "Добавление стрима")
    @MessageMapping("/admin/streams")
    @SendTo("/lines")
    public StreamDto addStream(@Payload StreamDto streamDto) throws Exception {
        Thread.sleep(1000);
        StreamDto saved = adminService.createStream(streamDto);
        return saved;
    }
}