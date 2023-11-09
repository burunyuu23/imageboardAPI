package org.dnlkk.repository;

import com.dnlkk.dependency_injector.annotations.components.Repository;
import com.dnlkk.repository.DnlkkRepository;
import org.dnlkk.model.Reply;

@Repository
public interface ReplyRepository extends DnlkkRepository<Integer, Reply> {
}
