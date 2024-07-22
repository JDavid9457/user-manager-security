package code.wolf.usermanagersecurity.infrastructure.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TokenResponseDTO<T> {
    private Integer status;
    private String message;
    private String error;
    private T data;
}
