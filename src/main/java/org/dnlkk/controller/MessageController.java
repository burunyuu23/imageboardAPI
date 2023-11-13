package org.dnlkk.controller;

import com.dnlkk.controller.annotations.*;
import com.dnlkk.controller.annotations.request_method.Get;
import com.dnlkk.controller.annotations.request_method.Post;
import com.dnlkk.controller.responses.ResponseEntity;
import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.RestController;
import com.dnlkk.doc.annotation.ApiOperation;
import com.dnlkk.doc.annotation.Tag;
import com.dnlkk.repository.helper.Pageable;
import com.dnlkk.repository.helper.Sort;
import org.dnlkk.controller.api.MessageControllerAPI;
import org.dnlkk.dto.request.MessageCreateRequestDTO;
import org.dnlkk.dto.response.AllMessageResponseDTO;
import org.dnlkk.model.Message;
import org.dnlkk.service.MessageService;

@RestController("/message")
@Tag(name = "Message", description = "Work with messages")
public class MessageController implements MessageControllerAPI {
    @AutoInject
    private MessageService messageService;

    @Get
    @RequestMapping("/:id")
    @ApiOperation(
            name = "Get one message with id",
            response = Message.class
    )
    @Override
    public ResponseEntity<Message> getMessage(@PathVar("id") Integer id) {
        return ResponseEntity.ok(messageService.getMessage(id));
    }

    @Get
    @RequestMapping("/rand")
    @ApiOperation(
            name = "Get one message with id",
            response = Message.class
    )
    @Override
    public ResponseEntity<Message> getRandomMessage(
            @RequestParam("thread") Integer threadId,
            @RequestParam("board") String boardId,
            @RequestParam("theme") Integer themeId
    ) {
        Pageable pageable = Pageable.builder()
                .limit(1)
                .sort(new Sort[]{new Sort("random()")})
                .build();
        if (threadId != null)
            return ResponseEntity.ok(messageService.getRandomMessageByThreadId(pageable, threadId));
        else if (boardId != null)
            return ResponseEntity.ok(messageService.getRandomMessageByBoardId(pageable, boardId));
        else if (themeId != null)
            return ResponseEntity.ok(messageService.getRandomMessageByThemeId(pageable, themeId));
        else
            return ResponseEntity.ok(messageService.getRandomMessage(pageable));

    }

    @Get
    @RequestMapping()
    @ApiOperation(
            name = "Get messages page",
            response = AllMessageResponseDTO.class
    )
    @Override
    public ResponseEntity<AllMessageResponseDTO> getAllMessages(
            @PageableParam Pageable pageable
    ) {
        return ResponseEntity.ok(new AllMessageResponseDTO(pageable, messageService.getAllMessages(pageable)));
    }

    @Post
    @RequestMapping()
    @ApiOperation(
            name = "Add one new message",
            response = Message.class
    )
    @Override
    public ResponseEntity<Message> postMessage(@RequestBody MessageCreateRequestDTO body) {
        return ResponseEntity.ok(messageService.postNewMessage(body));
    }
}
