package com.app.deliveryapp.domain.exceptionhandler;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler({EntidadeEmUsoException.class})
    public ResponseEntity<Object> negocioException(EntidadeEmUsoException ex, WebRequest request){
        Exception exception = Exception.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .dataHora(OffsetDateTime.now())
                .titulo(ex.getLocalizedMessage())
                .build();
        return handleExceptionInternal(ex,exception, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler({EntidadeNaoEncontradaException.class})
    public ResponseEntity<Object> exTest(EntidadeNaoEncontradaException ex, WebRequest request){
        Exception exception = Exception.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .dataHora(OffsetDateTime.now())
                .titulo(ex.getLocalizedMessage())
                .build();
        return handleExceptionInternal(ex,exception, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }
}
