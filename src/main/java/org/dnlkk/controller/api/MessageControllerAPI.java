package org.dnlkk.controller.api;

import com.dnlkk.controller.annotations.*;
import com.dnlkk.controller.responses.ResponseEntity;
import com.dnlkk.repository.helper.Pageable;
import org.dnlkk.dto.request.MessageCreateRequestDTO;
import org.dnlkk.dto.response.AllMessageResponseDTO;
import org.dnlkk.model.Message;

public interface MessageControllerAPI {

    ResponseEntity<Message> getMessage(@PathVar("id") Integer id);
    ResponseEntity<Message> getRandomMessage(
            @RequestParam("thread") Integer threadId,
            @RequestParam("board") String boardId,
            @RequestParam("theme") Integer themeId
    );

    ResponseEntity<AllMessageResponseDTO> getAllMessages(
            @RequestParam("thread") Integer threadId,
            @PageableParam Pageable pageable
    );

    ResponseEntity<?> postMessage(@RequestBody MessageCreateRequestDTO body);
}
