package org.dnlkk.repository;

import com.dnlkk.dependency_injector.annotations.components.Repository;
import com.dnlkk.repository.DnlkkRepository;
import com.dnlkk.repository.Pageable;
import org.dnlkk.model.Thread;

@Repository
public interface ThreadRepository extends DnlkkRepository<Integer, Thread> {
    Thread findByIdIgnoredMessagesAndBoard(Integer id);
    Thread findIgnoredBoardAndMessages(Pageable pageable);
    Thread findByBoardIgnoredBoardAndMessages(String boardId, Pageable pageable);
}
