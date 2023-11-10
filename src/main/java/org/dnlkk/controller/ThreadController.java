package org.dnlkk.controller;

import com.dnlkk.controller.annotations.PathVar;
import com.dnlkk.controller.annotations.RequestMapping;
import com.dnlkk.controller.annotations.RequestParam;
import com.dnlkk.controller.annotations.request_method.Get;
import com.dnlkk.controller.responses.ResponseEntity;
import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.RestController;
import com.dnlkk.doc.annotation.ApiOperation;
import com.dnlkk.repository.Pageable;
import com.dnlkk.repository.Sort;
import org.dnlkk.controller.api.ThreadControllerAPI;
import org.dnlkk.model.Thread;
import org.dnlkk.service.ThreadService;

@RestController("/thread")
public class ThreadController implements ThreadControllerAPI {
    @AutoInject
    private ThreadService threadService;

    @Get
    @RequestMapping("/:id")
    @ApiOperation(
            name = "Get thread",
            response = Thread.class
    )
    @Override
    public ResponseEntity<Thread> getThread(@PathVar("id") Integer id) {
        return ResponseEntity.ok(threadService.getThread(id));
    }

    @Get
    @RequestMapping("/rand")
    @ApiOperation(
            name = "Get random thread from board or from all",
            response = Thread.class
    )
    @Override
    public ResponseEntity<Thread> getRandomThread(@RequestParam("boardId") String boardId) {
        Pageable pageable = Pageable.builder()
                .limit(1)
                .sort(new Sort[]{new Sort("random()")})
                .build();
        if (boardId == null)
            return ResponseEntity.ok(threadService.getRandomThread(pageable));
        else
            return ResponseEntity.ok(threadService.getRandomThreadByBoardId(pageable, boardId));
    }
}
