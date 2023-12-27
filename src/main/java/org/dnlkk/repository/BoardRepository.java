package org.dnlkk.repository;

import com.dnlkk.dependency_injector.annotations.components.Repository;
import com.dnlkk.repository.DnlkkRepository;
import com.dnlkk.repository.helper.Pageable;
import org.dnlkk.model.Board;

import java.util.List;

@Repository
public interface BoardRepository extends DnlkkRepository<String, Board> {

    Board find(Pageable pageable);

    List<Board> findAllIgnoredBannerAndThreads();
    Board findByIdIgnoredThreads(String id);
    Board findByTheme(Integer themeId, Pageable pageable);
    Board findByThemeIgnoredBannerAndThreads(Integer themeId, Pageable pageable);
}
