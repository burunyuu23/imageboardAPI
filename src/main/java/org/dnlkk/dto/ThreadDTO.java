package org.dnlkk.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dnlkk.model.Thread;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreadDTO {
    @JsonUnwrapped
    private Thread thread;
    private Long messageCount;
    private Long messageToday;
}
