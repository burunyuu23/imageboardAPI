package org.dnlkk.dto.response;

import com.dnlkk.repository.helper.Pageable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dnlkk.dto.ThreadDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllThreadResponseDTO {
    private List<ThreadDTO> threadList;
    private Pageable pageable;
}
