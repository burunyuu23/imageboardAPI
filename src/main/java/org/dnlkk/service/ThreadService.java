package org.dnlkk.service;

import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.Service;
import com.dnlkk.repository.helper.Interval;
import com.dnlkk.repository.helper.Pageable;
import org.dnlkk.dto.ThreadDTO;
import org.dnlkk.dto.request.ThreadCreateRequestDTO;
import org.dnlkk.model.Board;
import org.dnlkk.model.Thread;
import org.dnlkk.repository.BoardRepository;
import org.dnlkk.repository.MessageRepository;
import org.dnlkk.repository.ThreadRepository;
import org.dnlkk.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThreadService {

    @AutoInject
    private MessageRepository messageRepository;
    @AutoInject
    private ThreadRepository threadRepository;
    @AutoInject
    private BoardRepository boardRepository;

    public List<Thread> getThreads(String boardId, Pageable pageable) {
        if (boardId == null)
            return threadRepository.findAllOnlyIgnoredBoard(pageable);
        return threadRepository.findByBoardOnlyIgnoredBoard(boardId, pageable);
    }


    public Thread getThread(Integer id) {
        return threadRepository.findByIdIgnoredBoardAndMessages(id);
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
