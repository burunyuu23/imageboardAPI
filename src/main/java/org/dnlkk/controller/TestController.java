package org.dnlkk.controller;

import com.dnlkk.controller.annotations.*;
import com.dnlkk.controller.responses.ResponseEntity;
import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.RestController;
import org.dnlkk.dto.Body;
import org.dnlkk.model.Board;
import org.dnlkk.model.Message;
import org.dnlkk.service.TestService;

@RestController("/test")
public class TestController {

    @AutoInject
    private TestService testService;

    @Get
    @RequestMapping("/:id")
    public ResponseEntity<String> getTest(@PathVar("id") String id){
        return ResponseEntity.ok(id);
    }

    @Get
    @RequestMapping("/msg/get/:id")
    public ResponseEntity<Message> getMsg(@PathVar("id") Integer id){
        return ResponseEntity.ok(testService.getMsg(id));
    }

    @Post
    @RequestMapping("/msg/create")
    public ResponseEntity<Message> postMessage(@RequestBody Body body){
        return ResponseEntity.ok(testService.postNewMessage(body.getBody()));
    }

    @Get
    @RequestMapping("/board/:id")
    public ResponseEntity<Board> getBoard(@PathVar("id") String id){
        return ResponseEntity.ok(testService.getBoard(id));
    }
}
