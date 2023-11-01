package org.dnlkk.repository;

import com.dnlkk.controller.annotations.RequestBody;
import com.dnlkk.dependency_injector.annotations.components.Repository;
import com.dnlkk.repository.DnlkkRepository;
import org.dnlkk.model.Thread;

@Repository
public interface ThreadRepository extends DnlkkRepository<Integer, Thread> {
}
