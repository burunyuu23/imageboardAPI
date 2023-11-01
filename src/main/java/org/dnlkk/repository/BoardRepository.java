package org.dnlkk.repository;

import com.dnlkk.dependency_injector.annotations.components.Repository;
import com.dnlkk.repository.DnlkkRepository;
import org.dnlkk.model.Board;

@Repository
public interface BoardRepository extends DnlkkRepository<String, Board> {
}
