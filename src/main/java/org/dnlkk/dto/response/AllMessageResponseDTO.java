package org.dnlkk.dto.response;

import com.dnlkk.repository.helper.Pageable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dnlkk.dto.MessageDTO;
import org.dnlkk.model.Message;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllMessageResponseDTO {
    private Pageable pageable;
    private List<MessageDTO> messages;
}

