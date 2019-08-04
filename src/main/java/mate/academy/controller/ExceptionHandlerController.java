package mate.academy.controller;

import mate.academy.controller.exception.NotMatchedPasswordsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleAll() {
        return ResponseEntity.status(409).body("Sorry, something went wrong");
    }

    @ExceptionHandler(NotMatchedPasswordsException.class)
    public ResponseEntity<String> passwordsNotMatch() {
        return ResponseEntity.status(409).body("Passwords doesn't match");
    }
}
