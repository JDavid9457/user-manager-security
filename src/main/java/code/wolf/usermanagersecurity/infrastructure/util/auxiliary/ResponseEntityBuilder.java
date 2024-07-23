package code.wolf.usermanagersecurity.infrastructure.util.auxiliary;

import code.wolf.usermanagersecurity.infrastructure.dto.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseEntityBuilder {
    public static <T> ResponseEntity<ResponseDTO<T>> buildSaveResponse(T data) {
        return ResponseEntity.ok(
                ResponseDTO.<T>builder()
                        .message("Exitoso")
                        .status(HttpStatus.OK.value())
                        .data(data)
                        .build());
    }

    public static <T> ResponseEntity<ResponseDTO<T>> buildFindByIdResponse(T data) {
        return ResponseEntity.ok(
                ResponseDTO.<T>builder()
                        .message("id")
                        .status(HttpStatus.OK.value())
                        .data(data)
                        .build());
    }



    public static <T> ResponseEntity<ResponseDTO<List<T>>> buildListResponse(List<T> data) {
        return ResponseEntity.ok(
                ResponseDTO.<List<T>>builder()
                        .message("Found")
                        .status(HttpStatus.OK.value())
                        .data(data)
                        .build());
    }

    public static ResponseEntity<ResponseDTO<Boolean>> buildDeletedResponse(boolean isDeleted) {
        return ResponseEntity.ok(
                ResponseDTO.<Boolean>builder()
                        .message(isDeleted ? "User deleted successfully" : "User not found")
                        .status(HttpStatus.OK.value())
                        .data(isDeleted)
                        .build());
    }


}
