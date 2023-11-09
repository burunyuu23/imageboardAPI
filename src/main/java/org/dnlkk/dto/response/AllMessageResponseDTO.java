package org.dnlkk.dto.response;

import com.dnlkk.repository.Pageable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dnlkk.model.Message;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllMessageResponseDTO {
    private Pageable pageable;
    private List<Message> messages;
}

