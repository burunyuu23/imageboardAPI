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
import org.dnlkk.dto.MessageDTO;
import org.dnlkk.dto.request.MessageCreateRequestDTO;
import org.dnlkk.dto.response.AllMessageResponseDTO;
import org.dnlkk.model.Message;
import org.dnlkk.service.MessageService;

import java.util.List;

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
    public ResponseEntity<MessageDTO> getMessage(@PathVar("id") Integer id) {
        Pageable pageable = Pageable.builder()
                .limit(1)
                .build();
        return ResponseEntity.ok(
                new MessageDTO(messageService.getMessage(id, pageable), messageService.getMessagePosition(id))
        );
    }

    @Get
    @RequestMapping("/rand")
    @ApiOperation(
            name = "Get random message from all/theme/board/thread",
            response = Message.class
    )
    @Override
    public ResponseEntity<MessageDTO> getRandomMessage(
            @RequestParam("thread") Integer threadId,
            @RequestParam("board") String boardId,
            @RequestParam("theme") Integer themeId
    ) {
        Pageable pageable = Pageable.builder()
                .limit(1)
                .sort(new Sort[]{new Sort("random()")})
                .build();
        Message message = null;
        if (threadId != null)
            message = messageService.getRandomMessageByThreadId(pageable, threadId);
        else if (boardId != null)
            message = messageService.getRandomMessageByBoardId(pageable, boardId);
        else if (themeId != null)
            message = messageService.getRandomMessageByThemeId(pageable, themeId);
        else
            message = messageService.getRandomMessage(pageable);

        return ResponseEntity.ok(
                new MessageDTO(message, messageService.getMessagePosition(message.getId()))
        );
    }

    @Post
    @RequestMapping("/list")
    @ApiOperation(
            name = "Get messages page",
            response = AllMessageResponseDTO.class
    )
    @Override
    public ResponseEntity<List<MessageDTO>> getMessagesInList(
            @RequestBody Integer[] messageIds
    ) {
        return ResponseEntity.ok(messageService.getMessagesInList(messageIds));
    }

    @Get
    @RequestMapping()
    @ApiOperation(
            name = "Get messages page",
            response = AllMessageResponseDTO.class
    )
    @Override
    public ResponseEntity<AllMessageResponseDTO> getAllMessages(
            @RequestParam("thread") Integer threadId,
            @PageableParam Pageable pageable
    ) {
        return ResponseEntity.ok(new AllMessageResponseDTO(pageable, messageService.getAllMessages(threadId, pageable)));
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
