package org.dnlkk.controller.api;

import com.dnlkk.controller.responses.ResponseEntity;
import org.dnlkk.model.Theme;

import java.util.List;

public interface ThemeControllerAPI {

    ResponseEntity<List<Theme>> getThemes();
}
