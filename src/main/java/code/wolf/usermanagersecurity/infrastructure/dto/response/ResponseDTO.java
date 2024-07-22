package code.wolf.usermanagersecurity.infrastructure.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseDTO<T> {
    private T data;
    private Integer status;
    private String message;
    private String error;
}
