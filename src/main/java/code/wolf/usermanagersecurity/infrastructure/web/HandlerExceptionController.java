package code.wolf.usermanagersecurity.infrastructure.web;


import code.wolf.usermanagersecurity.infrastructure.dto.response.ErrorModelDTO;
import code.wolf.usermanagersecurity.infrastructure.exceptions.EmailDuplicatedException;
import code.wolf.usermanagersecurity.infrastructure.exceptions.EmailNotValidException;
import code.wolf.usermanagersecurity.infrastructure.exceptions.PasswordNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptionController {


    @ExceptionHandler({EmailDuplicatedException.class, EmailNotValidException.class, PasswordNotValidException.class})
    public ResponseEntity<ErrorModelDTO> notFoundException(Exception e) {
        ErrorModelDTO errorModelDTO = ErrorModelDTO.builder()
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModelDTO);
    }

}
