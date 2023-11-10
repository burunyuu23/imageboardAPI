package org.dnlkk.repository;

import com.dnlkk.dependency_injector.annotations.components.Repository;
import com.dnlkk.repository.DnlkkRepository;
import com.dnlkk.repository.Pageable;
import org.dnlkk.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends DnlkkRepository<Integer, Message> {
    List<Message> findAll(Pageable pageable);
    Long countByThread(Integer id);
    Message find(Pageable pageable);
    Message findByThread(Integer threadId, Pageable pageable);
}
