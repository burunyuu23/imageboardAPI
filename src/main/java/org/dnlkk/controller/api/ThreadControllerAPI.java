package org.dnlkk.controller.api;

import com.dnlkk.controller.annotations.RequestParam;
import com.dnlkk.controller.responses.ResponseEntity;
import org.dnlkk.model.Thread;

public interface ThreadControllerAPI {
    ResponseEntity<Thread> getThread(@RequestParam("id") Integer id);
}
