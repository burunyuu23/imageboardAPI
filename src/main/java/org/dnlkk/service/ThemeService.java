package org.dnlkk.service;

import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.Service;
import org.dnlkk.model.Theme;
import org.dnlkk.repository.ThemeRepository;

import java.util.List;

@Service
public class ThemeService {

    @AutoInject
    private ThemeRepository themeRepository;

    public List<Theme> getThemes() {
        return themeRepository.findAll();
    }
}
