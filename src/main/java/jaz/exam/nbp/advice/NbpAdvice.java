package jaz.exam.nbp.advice;

import jaz.exam.nbp.exception.BadRequestException;
import jaz.exam.nbp.exception.InternalServerErrorException;
import jaz.exam.nbp.exception.NotFoundRateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;

@ControllerAdvice
public class NbpAdvice {

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Void> handleInternalServerError(InternalServerErrorException ex) {
        return ResponseEntity.status(502).build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Void> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(NotFoundRateException.class)
    public ResponseEntity<Void> handleNotFoundMovieException(NotFoundRateException ex) {
        return ResponseEntity.status(404).build();
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Void> handleConnectionException(ConnectException ex) {
        return ResponseEntity.status(504).build();
    }
}
