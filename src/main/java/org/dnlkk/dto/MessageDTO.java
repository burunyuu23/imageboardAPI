package org.dnlkk.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dnlkk.model.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    @JsonUnwrapped
    private Message message;
    private Long positionInThread;
}
