package org.dnlkk.controller;

import com.dnlkk.controller.annotations.RequestMapping;
import com.dnlkk.controller.annotations.request_method.Get;
import com.dnlkk.controller.responses.ResponseEntity;
import com.dnlkk.dependency_injector.annotations.AutoInject;
import com.dnlkk.dependency_injector.annotations.components.RestController;
import org.dnlkk.controller.api.ThemeControllerAPI;
import org.dnlkk.model.Theme;
import org.dnlkk.service.ThemeService;

import java.util.List;

@RestController("/theme")
public class ThemeController implements ThemeControllerAPI {
    @AutoInject
    private ThemeService themeService;

    @Get
    @RequestMapping()
    @Override
    public ResponseEntity<List<Theme>> getThemes() {
        return ResponseEntity.ok(themeService.getThemes());
    }
}
