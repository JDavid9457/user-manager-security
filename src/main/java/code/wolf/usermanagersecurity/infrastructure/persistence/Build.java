package code.wolf.usermanagersecurity.infrastructure.persistence;

import code.wolf.usermanagersecurity.infrastructure.dto.response.ResponseDTO;
import org.springframework.http.ResponseEntity;

public class Build {
    public static <T> ResponseEntity<ResponseDTO<T>> build200Response(T data) {
        return ResponseEntity.ok(
                ResponseDTO.<T>builder()
                        .message("Exitoso")
                        .status(200)
                        .data(data)
                        .build());
    }
}
