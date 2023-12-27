package org.dnlkk.dto.request;

import lombok.Data;

@Data
public class MessageCreateRequestDTO {
    String body;
    Integer[] attachmentIds;
    Integer threadId;
}
