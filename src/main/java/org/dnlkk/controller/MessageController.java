package org.dnlkk.controller;

import com.dnlkk.controller.annotations.*;
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
    @RequestMapping("/msg/:id")
    @ApiOperation(
            name = "Get one message with id",
            response = Message.class
    )
    @Override
    public ResponseEntity<Message> getMessage(@PathVar("id") Integer id) {
        return ResponseEntity.ok(messageService.getMessage(id));
    }

    @Get
    @RequestMapping("/msg")
    @ApiOperation(
            name = "Get messages page",
            response = AllMessageResponseDTO.class)
    @Override
    public ResponseEntity<AllMessageResponseDTO> getAllMessages(
            @RequestParam("limit") Integer limit,
            @RequestParam("page") Integer page,
            @RequestParam("offset") Integer offset
    ) {
        Pageable pageable = Pageable.builder()
                .limit(limit != null ? limit : 10)
                .page(page != null ? page : 0)
                .offset(offset != null ? offset : 0)
                .build();
        return ResponseEntity.ok(new AllMessageResponseDTO(messageService.getAllMessages(pageable)));
    }

    @Post
    @RequestMapping("/msg")
    @ApiOperation(
            name = "Add one new message",
            response = Message.class)
    @Override
    public ResponseEntity<Message> postMessage(@RequestBody MessageCreateRequestDTO body) {
        return ResponseEntity.ok(messageService.postNewMessage(body));
    }
}
