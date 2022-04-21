package com.app.deliveryapp.domain.exceptionhandler;

import lombok.AllArgsConstructor;
import org.modelmapper.spi.PropertyType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
     /*  body = Exception.builder()
               .title(status.getReasonPhrase())
               .detail(ex.getLocalizedMessage())
               .status(status.value())
               .type("localhost://site.com.br/entidade-nao-encontrada" )
               .build();*/



        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler({EntidadeEmUsoException.class})
    public ResponseEntity<Object> negocioException(EntidadeEmUsoException ex, WebRequest request){

        Exception exception = createExcptionBuilder(HttpStatus.CONFLICT, ExceptionType.ENTIDADE_EM_USO, ex.getMessage()).build();
        return handleExceptionInternal(ex,exception, new HttpHeaders(), HttpStatus.CONFLICT, request);

    }

    @ExceptionHandler({EntidadeNaoEncontradaException.class})
    public ResponseEntity<Object> exTest(EntidadeNaoEncontradaException ex, WebRequest request){
        Exception exception = createExcptionBuilder(HttpStatus.NOT_FOUND, ExceptionType.ENITDADE_NAO_ENCONTRADA, ex.getMessage()).build();

        return handleExceptionInternal(ex,exception, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


        Exception exception = createExcptionBuilder(status, ExceptionType.MENSAGEM_BODY_IMCOMPREEMSIVEL, "O corpo da requisição está invalido").build();

        return handleExceptionInternal(ex,exception, new HttpHeaders(), status, request);
    }

    private Exception.ExceptionBuilder createExcptionBuilder(HttpStatus status, ExceptionType exceptionType, String detail){

        return Exception.builder()
                .status(status.value())
                .type(exceptionType.getUri())
                .title(exceptionType.getTitle())
                .detail(detail);
    }
}
