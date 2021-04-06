package com.github.ericomonteiro.smartstock.config.error;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.ericomonteiro.smartstock.config.error.ErrorKeys.General.UNEXPECTED_ERROR;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {
    private final MessageSource apiErrorMessageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, Locale locale) {
        Stream<ObjectError> errors = e.getBindingResult().getAllErrors().stream();

        List<ErrorResponse.ApiError> apiErrors = errors
                .map(ObjectError::getDefaultMessage)
                .map(code -> toApiError(code, locale))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, apiErrors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, Locale locale) {
        ErrorResponse.ApiError apiError = toApiError(UNEXPECTED_ERROR, locale);
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, apiError);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    private ErrorResponse.ApiError toApiError(String code, Locale locale) {
        return toApiError(code, locale, null);
    }


    private ErrorResponse.ApiError toApiError(String code, Locale locale, Object... args) {
        String message;

        try {
            message = apiErrorMessageSource.getMessage(code, args, locale);
        } catch (NoSuchMessageException e) {
            message = "NO_MESSAGE_AVAILABLE";
        }

        return new ErrorResponse.ApiError(code, message);
    }

}
