package org.dnlkk.repository;

import com.dnlkk.dependency_injector.annotations.components.Repository;
import com.dnlkk.repository.DnlkkRepository;
import com.dnlkk.repository.annotations.Param;
import com.dnlkk.repository.annotations.Query;
import com.dnlkk.repository.helper.Interval;
import com.dnlkk.repository.helper.Pageable;
import org.dnlkk.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends DnlkkRepository<Integer, Message> {
    List<Message> findAll(Pageable pageable);
    Long countByThread(Integer id);
    @Query(value = "SELECT COUNT( DISTINCT message_table.id ) " +
            "FROM message_table " +
            "WHERE message_table.thread_id = :thread " +
            "AND message_table.created_date >= CURRENT_TIMESTAMP - INTERVAL '1 day'", autoReference = false)
    Long countByToday(@Param("thread") Integer threadId);
    Long countByThreadAndGtCreatedInterval(Integer threadId, Interval interval);
    Message find(Pageable pageable);
    Message findByThread(Integer threadId, Pageable pageable);
}
