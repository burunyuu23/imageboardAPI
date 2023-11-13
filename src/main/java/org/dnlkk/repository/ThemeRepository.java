package org.dnlkk.repository;

import com.dnlkk.dependency_injector.annotations.components.Repository;
import com.dnlkk.repository.DnlkkRepository;
import org.dnlkk.model.Theme;

import java.util.List;

@Repository
public interface ThemeRepository extends DnlkkRepository<Integer, Theme> {
    List<Theme> findAllIgnoredBoards();
}
