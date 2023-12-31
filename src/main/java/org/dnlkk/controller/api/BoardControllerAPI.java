package org.dnlkk.controller.api;

import com.dnlkk.controller.annotations.PathVar;
import com.dnlkk.controller.responses.ResponseEntity;
import org.dnlkk.model.Board;

import java.util.List;

public interface BoardControllerAPI {
    ResponseEntity<List<Board>> getBoards();
    ResponseEntity<Board> getBoard(@PathVar("id") String id);
    ResponseEntity<Board> getRandomBoard(@PathVar("theme") Integer themeId);
}
