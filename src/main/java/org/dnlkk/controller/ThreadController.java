package org.dnlkk.controller;

import com.dnlkk.controller.annotations.PathVar;
import com.dnlkk.controller.annotations.RequestMapping;
import com.dnlkk.controller.annotations.request_method.Get;
import com.dnlkk.controller.responses.ResponseEntity;
import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.RestController;
import com.dnlkk.doc.annotation.ApiOperation;
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
}
