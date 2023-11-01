package org.dnlkk.controller;

import com.dnlkk.controller.annotations.*;
import com.dnlkk.controller.responses.ResponseEntity;
import com.dnlkk.dependency_injector.annotations.components.Controller;
import org.dnlkk.dto.Body;

@Controller("/test.html")
public class TestedController {

    @Get
    @RequestMapping("/:id")
    public ResponseEntity<Body> getMessage(@PathVar("id") Integer id) {
        System.out.println(id);
        return ResponseEntity.bad(new Body(id.toString()));
    }
    @Get
    @RequestMapping("/:id/limit")
    public ResponseEntity<String> getParamMessage(@PathVar("id") Integer id, @RequestParam("limit") Integer limit) {
        return ResponseEntity.ok(limit.toString());
    }
    @Post
    @RequestMapping("/:id")
    public ResponseEntity<Body> postMessage(@PathVar("id") Integer id, @RequestBody Body body) {
        return ResponseEntity.ok(body);
    }
}
