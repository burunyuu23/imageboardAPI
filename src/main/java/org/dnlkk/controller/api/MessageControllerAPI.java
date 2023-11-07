package org.dnlkk.controller.api;

import com.dnlkk.controller.annotations.*;
import com.dnlkk.controller.responses.ResponseEntity;
import org.dnlkk.dto.request.MessageCreateRequestDTO;
import org.dnlkk.dto.response.AllMessageResponseDTO;
import org.dnlkk.model.Message;

public interface MessageControllerAPI {

    ResponseEntity<Message> getMessage(@PathVar("id") Integer id);

    ResponseEntity<AllMessageResponseDTO> getAllMessages(
            @RequestParam("limit") Integer limit,
            @RequestParam("page") Integer page,
            @RequestParam("offset") Integer offset
    );

    ResponseEntity<Message> postMessage(@RequestBody MessageCreateRequestDTO body);
}
