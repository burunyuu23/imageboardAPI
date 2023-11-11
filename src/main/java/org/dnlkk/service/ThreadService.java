package org.dnlkk.service;

import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.Service;
import com.dnlkk.repository.Pageable;
import org.dnlkk.dto.request.ThreadCreateRequestDTO;
import org.dnlkk.model.Board;
import org.dnlkk.model.Thread;
import org.dnlkk.repository.BoardRepository;
import org.dnlkk.repository.ThreadRepository;
import org.dnlkk.utils.StringUtils;

@Service
public class ThreadService {

    @AutoInject
    private ThreadRepository threadRepository;
    @AutoInject
    private BoardRepository boardRepository;

    public Thread getThread(Integer id) {
        return threadRepository.findById(id);
    }


    public Thread getRandomThread(Pageable pageable) {
        return threadRepository.findIgnoredBoardAndMessages(pageable);
    }

    public Thread getRandomThreadByTheme(Pageable pageable, Integer themeId) {
        Board board = boardRepository.findByThemeIgnoredBannerAndThreads(themeId, pageable);
        return threadRepository.findByBoardIgnoredBoardAndMessages(board.getId(), pageable);
    }

    public Thread getRandomThreadByBoardId(Pageable pageable, String boardId) {
        return threadRepository.findByBoardIgnoredBoardAndMessages(boardId, pageable);
    }

    public Thread postNewThread(ThreadCreateRequestDTO threadCreateRequestDTO) {
        Thread thread = new Thread();
        if (threadCreateRequestDTO.getName().isBlank())
            thread.setName(StringUtils.truncateAndAppendEllipsis(threadCreateRequestDTO.getMainMessage().getBody(), 100));
        else
            thread.setName(threadCreateRequestDTO.getName());

        thread.setBoard(boardRepository.findByIdIgnoredBannerAndThreads(threadCreateRequestDTO.getBoardId()));
        return threadRepository.save(thread);
    }
}
