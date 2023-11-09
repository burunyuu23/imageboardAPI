package org.dnlkk.controller.api;

import com.dnlkk.controller.annotations.*;
import com.dnlkk.controller.responses.ResponseEntity;
import com.dnlkk.repository.Pageable;
import org.dnlkk.dto.request.MessageCreateRequestDTO;
import org.dnlkk.dto.response.AllMessageResponseDTO;
import org.dnlkk.model.Message;

public interface MessageControllerAPI {

    ResponseEntity<Message> getMessage(@PathVar("id") Integer id);

    ResponseEntity<AllMessageResponseDTO> getAllMessages(@PageableParam Pageable pageable);

    ResponseEntity<Message> postMessage(@RequestBody MessageCreateRequestDTO body);
}
