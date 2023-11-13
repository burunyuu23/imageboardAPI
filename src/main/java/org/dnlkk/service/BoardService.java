package org.dnlkk.service;

import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.repository.helper.Pageable;
import org.dnlkk.model.Board;
import org.dnlkk.repository.BoardRepository;

public class BoardService {
    @AutoInject
    private BoardRepository boardRepository;

    public Board getBoard(String id) {
        return boardRepository.findById(id);
    }

    public Board getRandomBoardByThemeId(Pageable pageable, Integer themeId) {
        return boardRepository.findByTheme(themeId, pageable);
    }
    public Board getRandomBoard(Pageable pageable) {
        return boardRepository.find(pageable);
    }

}
