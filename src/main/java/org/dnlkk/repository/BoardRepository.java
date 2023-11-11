package org.dnlkk.repository;

import com.dnlkk.dependency_injector.annotations.components.Repository;
import com.dnlkk.repository.DnlkkRepository;
import com.dnlkk.repository.Pageable;
import org.dnlkk.model.Board;

@Repository
public interface BoardRepository extends DnlkkRepository<String, Board> {

    Board find(Pageable pageable);
    Board findByIdIgnoredBannerAndThreads(String id);
    Board findByTheme(Integer themeId, Pageable pageable);
    Board findByThemeIgnoredBannerAndThreads(Integer themeId, Pageable pageable);
}
