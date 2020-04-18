package bank.api.v1.exception;

import bank.api.v1.dto.ErrorDto;
import bank.domain.exception.AlreadyExistsException;
import bank.domain.exception.InvalidArgumentException;
import bank.domain.exception.MissingParameterException;
import bank.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.junit.Assert.*;

public class ErrorHandlerTest {

    private static final String ALREADY_EXIST_EXCEPTION_MESSAGE = "alreadyExistException";
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "notFoundException";
    private static final String MISSING_PARAMETER_EXCEPTION_MESSAGE = "missingParameterException";
    private static final String INVALID_ARGUMENT_EXCEPTION_MESSAGE = "invalidArgumentException";

    private ErrorHandler testedClass;

    @Before
    public void setUp() {
        testedClass = new ErrorHandler();
    }

    @Test
    public void givenAMethodArgumentTypeMismatchException_whenHandleMethodArgumentTypeMismatch_thenHttpStatusMustBeBadRequest() {
        MethodArgumentTypeMismatchException ex = new MethodArgumentTypeMismatchException(null, null, "name", null, null);

        ResponseEntity<ErrorDto> result = testedClass.handleMethodArgumentTypeMismatch(ex);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void givenAMissingParameterException_whenHandleMissingParameter_thenHttpStatusMustBeBadRequest() {
        MissingParameterException ex = new MissingParameterException(MISSING_PARAMETER_EXCEPTION_MESSAGE);

        ResponseEntity<ErrorDto> result = testedClass.handleMissingParameterException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void givenAnAlreadyExistsException_whenHandleAlreadyExists_thenHttpStatusMustBeConflict() {
        AlreadyExistsException ex = new AlreadyExistsException(ALREADY_EXIST_EXCEPTION_MESSAGE);

        ResponseEntity<ErrorDto> result = testedClass.handleAlreadyExist(ex);

        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }

    @Test
    public void givenANotFoundException_whenHandleNotFound_thenHttpStatusMustBeNotFound() {
        NotFoundException ex = new NotFoundException(NOT_FOUND_EXCEPTION_MESSAGE);

        ResponseEntity<ErrorDto> result = testedClass.handleNotFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void givenAMethodArgumentTypeMismatchException_whenHandleMethodArgumentTypeMismatch_thenBodyShouldContainsExceptionMessage() {
        MethodArgumentTypeMismatchException ex = new MethodArgumentTypeMismatchException(null, null, "name", null, null);

        ResponseEntity<ErrorDto> result = testedClass.handleMethodArgumentTypeMismatch(ex);

        assertEquals("Failed to convert value of type 'null'", result.getBody().getMessage());
    }

    @Test
    public void givenAnAlreadyExistsException_whenHandleAlreadyExists_thenBodyShouldContainsExceptionMessage() {
        AlreadyExistsException ex = new AlreadyExistsException(ALREADY_EXIST_EXCEPTION_MESSAGE);

        ResponseEntity<ErrorDto> result = testedClass.handleAlreadyExist(ex);

        assertEquals(ALREADY_EXIST_EXCEPTION_MESSAGE, result.getBody().getMessage());
    }

    @Test
    public void givenANotFoundException_whenHandleNotFound_thenBodyShouldContainsExceptionMessage() {
        NotFoundException ex = new NotFoundException(NOT_FOUND_EXCEPTION_MESSAGE);

        ResponseEntity<ErrorDto> result = testedClass.handleNotFound(ex);

        assertEquals(NOT_FOUND_EXCEPTION_MESSAGE, result.getBody().getMessage());
    }

    @Test
    public void givenAMissingParameterException_whenHandleMissingParameter_thenBodyShouldContainsExceptionMessage() {
        MissingParameterException ex = new MissingParameterException(MISSING_PARAMETER_EXCEPTION_MESSAGE);

        ResponseEntity<ErrorDto> result = testedClass.handleMissingParameterException(ex);

        assertEquals(MISSING_PARAMETER_EXCEPTION_MESSAGE, result.getBody().getMessage());
    }

    @Test
    public void givenAInvalidParameterException_whenHandleMissingParameter_thenBodyShouldContainsExceptionMessage() {
        InvalidArgumentException ex = new InvalidArgumentException(INVALID_ARGUMENT_EXCEPTION_MESSAGE);

        ResponseEntity<ErrorDto> result = testedClass.handleInvalidArgumentExceptionException(ex);

        assertEquals(INVALID_ARGUMENT_EXCEPTION_MESSAGE, result.getBody().getMessage());
    }
}