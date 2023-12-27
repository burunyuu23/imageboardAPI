package org.dnlkk.repository;

import com.dnlkk.dependency_injector.annotations.components.Repository;
import com.dnlkk.repository.DnlkkRepository;
import com.dnlkk.repository.annotations.Param;
import com.dnlkk.repository.annotations.Query;
import com.dnlkk.repository.helper.Interval;
import com.dnlkk.repository.helper.Pageable;
import org.dnlkk.model.Attachment;
import org.dnlkk.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends DnlkkRepository<Integer, Message> {
    List<Message> findAll(Pageable pageable);
    List<Message> findInId(Integer[] ids);

    Message findById(Integer id, Pageable pageable);

    @Query(value = "SELECT pos " +
            "FROM (" +
            "    SELECT ROW_NUMBER() OVER (PARTITION BY thread_id ORDER BY message_table.id) as pos,message_table.id" +
            "    FROM message_table " +
            "    LEFT JOIN thread_table ON message_table.thread_id = thread_table.id " +
            "    LEFT JOIN board_table ON board_table.id = thread_table.board_id" +
            ") as Z " +
            "WHERE Z.id = :id", autoReference = false)
    Long positionById(@Param("id") Integer id);

    Long countByThread(Integer id);

    @Query(value = "SELECT COUNT( DISTINCT message_table.id ) " +
            "FROM message_table " +
            "WHERE message_table.thread_id = :thread " +
            "AND message_table.created_date >= CURRENT_TIMESTAMP - INTERVAL '1 day'", autoReference = false)
    Long countByToday(@Param("thread") Integer threadId);

    Long countByThreadAndGtCreatedInterval(Integer threadId, Interval interval);

    Message find(Pageable pageable);

    List<Message> findByThread(Integer threadId, Pageable pageable);
}
