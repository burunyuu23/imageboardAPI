package org.dnlkk.controller;

import com.dnlkk.controller.annotations.RequestMapping;
import com.dnlkk.controller.annotations.RequestParam;
import com.dnlkk.controller.annotations.request_method.Get;
import com.dnlkk.controller.responses.ResponseEntity;
import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.RestController;
import com.dnlkk.doc.annotation.ApiOperation;
import com.dnlkk.repository.helper.Pageable;
import com.dnlkk.repository.helper.Sort;
import org.dnlkk.controller.api.BoardControllerAPI;
import org.dnlkk.dto.response.AllMessageResponseDTO;
import org.dnlkk.model.Board;
import org.dnlkk.service.BoardService;

@RestController("/board")
public class BoardController implements BoardControllerAPI {
    @AutoInject
    private BoardService boardService;

    @Get
    @RequestMapping()
    @ApiOperation(
            name = "Get board by id",
            response = AllMessageResponseDTO.class
    )
    @Override
    public ResponseEntity<Board> getBoard(String id) {
        return ResponseEntity.ok(boardService.getBoard(id));
    }

    @Get
    @RequestMapping("/rand")
    @ApiOperation(
            name = "Get random board from all/one theme",
            response = AllMessageResponseDTO.class
    )
    @Override
    public ResponseEntity<Board> getRandomBoard(@RequestParam("theme") Integer themeId) {
        Pageable pageable = Pageable.builder()
                .limit(1)
                .sort(new Sort[]{new Sort("random()")})
                .build();
        if (themeId == null)
            return ResponseEntity.ok(boardService.getRandomBoard(pageable));
        else
            return ResponseEntity.ok(boardService.getRandomBoardByThemeId(pageable, themeId));
    }
}
