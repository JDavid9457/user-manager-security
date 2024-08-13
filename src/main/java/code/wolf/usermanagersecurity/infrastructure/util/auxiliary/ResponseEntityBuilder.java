package code.wolf.usermanagersecurity.infrastructure.util.auxiliary;

import code.wolf.usermanagersecurity.infrastructure.dto.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static code.wolf.usermanagersecurity.infrastructure.util.constants.Messages.*;

public class ResponseEntityBuilder {
    public static <T> ResponseEntity<ResponseDTO<T>> buildSaveResponse(T data) {
        return ResponseEntity.ok(
                ResponseDTO.<T>builder()
                        .message(USER_CREATION_SUCCESS)
                        .status(HttpStatus.CREATED.value())
                        .data(data)
                        .build());
    }

    public static <T> ResponseEntity<ResponseDTO<T>> buildFindByIdResponse(T data) {
        return ResponseEntity.ok(
                ResponseDTO.<T>builder()
                        .message(ID_FOUND)
                        .status(HttpStatus.OK.value())
                        .data(data)
                        .build());
    }

    public static <T> ResponseEntity<ResponseDTO<List<T>>> buildListResponse(List<T> data) {
        return ResponseEntity.ok(
                ResponseDTO.<List<T>>builder()
                        .message(LIST_FOUND)
                        .status(HttpStatus.OK.value())
                        .data(data)
                        .build());
    }

    public static ResponseEntity<ResponseDTO<Boolean>> buildDeletedResponse(boolean isDeleted) {
        return ResponseEntity.ok(
                ResponseDTO.<Boolean>builder()
                        .message(isDeleted ? USER_DELETED_SUCCESSFULLY : USER_NOT_FOUND)
                        .status(HttpStatus.OK.value())
                        .data(isDeleted)
                        .build());
    }


}
