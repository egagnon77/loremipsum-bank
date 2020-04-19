package bank.api.v1.exception;

import bank.api.v1.dto.ErrorDto;
import bank.domain.exception.AlreadyExistsException;
import bank.domain.exception.InvalidArgumentException;
import bank.domain.exception.MissingParameterException;
import bank.domain.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<ErrorDto> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AlreadyExistsException.class})
    protected ResponseEntity<ErrorDto> handleAlreadyExist(AlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<ErrorDto> handleNotFound(NotFoundException ex) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MissingParameterException.class})
    protected ResponseEntity<ErrorDto> handleMissingParameterException(MissingParameterException ex) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidArgumentException.class})
    protected ResponseEntity<ErrorDto> handleInvalidArgumentExceptionException(InvalidArgumentException ex) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
