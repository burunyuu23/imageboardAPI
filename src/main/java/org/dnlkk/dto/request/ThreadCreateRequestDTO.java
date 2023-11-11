package org.dnlkk.dto.request;

import lombok.Data;

@Data
public class ThreadCreateRequestDTO {
    private String boardId;
    private String name;
    private MessageCreateRequestDTO mainMessage;
}
