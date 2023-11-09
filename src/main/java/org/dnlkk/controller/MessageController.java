package org.dnlkk.controller;

import com.dnlkk.controller.annotations.*;
import com.dnlkk.controller.annotations.request_method.Get;
import com.dnlkk.controller.annotations.request_method.Post;
import com.dnlkk.controller.responses.ResponseEntity;
import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.RestController;
import com.dnlkk.doc.annotation.ApiOperation;
import com.dnlkk.doc.annotation.Tag;
import com.dnlkk.repository.Pageable;
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
