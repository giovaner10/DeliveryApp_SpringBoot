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


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(java.lang.Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
       body = Exception.builder()
               .titulo(ex.getLocalizedMessage())
               .menssagem(status.getReasonPhrase())
               .status(status.value())
               .dataHora(OffsetDateTime.now())
               .build();


        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler({EntidadeEmUsoException.class})
    public ResponseEntity<Object> negocioException(EntidadeEmUsoException ex, WebRequest request){
        Exception exception = Exception.builder().build();
        return handleExceptionInternal(ex,exception, new HttpHeaders(), HttpStatus.CONFLICT, request);

    }

    @ExceptionHandler({EntidadeNaoEncontradaException.class})
    public ResponseEntity<Object> exTest(EntidadeNaoEncontradaException ex, WebRequest request){
        Exception exception = Exception.builder().build();
        return handleExceptionInternal(ex,exception, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }
}
