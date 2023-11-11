package org.dnlkk.controller.api;

import com.dnlkk.controller.annotations.RequestBody;
import com.dnlkk.controller.annotations.RequestParam;
import com.dnlkk.controller.responses.ResponseEntity;
import org.dnlkk.dto.request.ThreadCreateRequestDTO;
import org.dnlkk.model.Thread;

public interface ThreadControllerAPI {
    ResponseEntity<Thread> getThread(@RequestParam("id") Integer id);

    ResponseEntity<Thread> getRandomThread(
            @RequestParam("boardId") String boardId,
            @RequestParam("themeId") Integer themeId
    );

    ResponseEntity<?> postThread(@RequestBody ThreadCreateRequestDTO threadCreateRequestDTO);
}
