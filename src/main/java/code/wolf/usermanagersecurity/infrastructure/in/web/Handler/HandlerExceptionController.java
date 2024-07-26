package code.wolf.usermanagersecurity.infrastructure.in.web.Handler;


import code.wolf.usermanagersecurity.infrastructure.dto.response.ErrorModelDTO;
import code.wolf.usermanagersecurity.infrastructure.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({EmailDuplicatedException.class, EmailNotValidException.class, PasswordNotValidException.class,
            UserNotFoundException.class, EmailNotFoudException.class, UserSaveException.class, UserUpdateException.class})
    public ResponseEntity<ErrorModelDTO> notFoundException(Exception e) {
        ErrorModelDTO errorModelDTO = ErrorModelDTO.builder()
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModelDTO);
    }

}
