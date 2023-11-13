package org.dnlkk.repository;

import com.dnlkk.dependency_injector.annotations.components.Repository;
import com.dnlkk.repository.DnlkkRepository;
import com.dnlkk.repository.helper.Pageable;
import org.dnlkk.model.Thread;

import java.util.List;

@Repository
public interface ThreadRepository extends DnlkkRepository<Integer, Thread> {
    List<Thread> findAll(Pageable pageable);
    List<Thread> findByBoardOnlyIgnoredBoard(String boardId, Pageable pageable);
    List<Thread> findAllOnlyIgnoredBoard(Pageable pageable);
    Long countById(Integer id);

    Thread findByIdIgnoredBoardAndMessages(Integer id);
    Thread findIgnoredBoardAndMessages(Pageable pageable);
    Thread findByBoardIgnoredBoardAndMessages(String boardId, Pageable pageable);
}
