package org.dnlkk.controller.api;

import com.dnlkk.controller.annotations.PageableParam;
import com.dnlkk.controller.annotations.RequestBody;
import com.dnlkk.controller.annotations.RequestParam;
import com.dnlkk.controller.responses.ResponseEntity;
import com.dnlkk.repository.Pageable;
import org.dnlkk.dto.request.ThreadCreateRequestDTO;
import org.dnlkk.dto.response.AllThreadResponseDTO;
import org.dnlkk.model.Thread;

public interface ThreadControllerAPI {

    ResponseEntity<AllThreadResponseDTO> getThreads(@PageableParam Pageable pageable);

    ResponseEntity<Thread> getThread(@RequestParam("id") Integer id);

    ResponseEntity<Thread> getRandomThread(
            @RequestParam("boardId") String boardId,
            @RequestParam("themeId") Integer themeId
    );

    ResponseEntity<Thread> postThread(@RequestBody ThreadCreateRequestDTO threadCreateRequestDTO);
}
