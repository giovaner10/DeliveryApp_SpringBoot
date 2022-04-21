package com.app.deliveryapp.domain.exceptionhandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.event.EventUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.modelmapper.spi.PropertyType;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(java.lang.Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String detail = "O item que apresenta erros no seu corpo, verifique e tente novamenete";

        BindingResult bindingResult = ex.getBindingResult();

        List<Exception.Field> problemField = bindingResult
                .getFieldErrors()
                .stream()
                .map(fieldError ->
                        Exception.Field.builder()
                        .name(fieldError.getField())
                        .UserMesage(fieldError.getDefaultMessage())
                        .build()
                )
                .collect(Collectors.toList());

        Exception exception = createExcptionBuilder(status, ExceptionType.DADOS_INVALIDOS, detail)
                .fields(problemField)
                .build();

        return handleExceptionInternal(ex,exception, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.",
                ex.getRequestURL());

        Exception exception = createExcptionBuilder(status, ExceptionType.PARAMETRO_INVALIDO, detail).build();

        return handleExceptionInternal(ex,exception, headers, status, request);
    }


    @ExceptionHandler({EntidadeEmUsoException.class})
    public ResponseEntity<Object> negocioException(EntidadeEmUsoException ex, WebRequest request){

        Exception exception = createExcptionBuilder(HttpStatus.CONFLICT, ExceptionType.ENTIDADE_EM_USO, ex.getMessage()).build();
        return handleExceptionInternal(ex,exception, new HttpHeaders(), HttpStatus.CONFLICT, request);

    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handlerMethodArgumentTypeMismatchException(
                    (MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }



    public ResponseEntity<Object> handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request){


        String detail = String.format("A url '%s' recebeu o valor '%s' , que é um tipo invalido." +
                " corrija e informe uma do tipo '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        Exception exception = createExcptionBuilder(status, ExceptionType.PARAMETRO_INVALIDO, detail).build();
        return handleExceptionInternal(ex,exception, new HttpHeaders(), HttpStatus.CONFLICT, request);

    }

    @ExceptionHandler({EntidadeNaoEncontradaException.class})
    public ResponseEntity<Object> exTest(EntidadeNaoEncontradaException ex, WebRequest request){



        Exception exception = createExcptionBuilder(HttpStatus.NOT_FOUND, ExceptionType.ENITDADE_NAO_ENCONTRADA, ex.getMessage()).build();

        return handleExceptionInternal(ex,exception, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if(rootCause instanceof InvalidFormatException){
            return handlerInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        }else  if(rootCause instanceof PropertyBindingException){
            return handlerInvalidFormatException((PropertyBindingException) rootCause, headers, status, request);

        }


            Exception exception = createExcptionBuilder(status, ExceptionType.MENSAGEM_BODY_IMCOMPREEMSIVEL, "O corpo da requisição está invalido").build();

        return handleExceptionInternal(ex,exception, new HttpHeaders(), status, request);
    }

    private Exception.ExceptionBuilder createExcptionBuilder(HttpStatus status, ExceptionType exceptionType, String detail){

        return Exception.builder()
                .status(status.value())
                .type(exceptionType.getUri())
                .title(exceptionType.getTitle())
                .dataOcourence(OffsetDateTime.now())
                .detail(detail);
    }


    private ResponseEntity<Object> handlerInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

        String path = ex.getPath()
                .stream()
                .map(reference -> reference.getFieldName())
                .collect(Collectors.joining("."));
       String detail = String.format("A propiedade '%s' recebeu o valor '%s' , que é um tipo invalido." +
               " corrija e informe uma do tipo '%s'", path, ex.getValue(), ex.getTargetType().getName());
        Exception exception = createExcptionBuilder(status, ExceptionType.MENSAGEM_BODY_IMCOMPREEMSIVEL, detail).build();

        return handleExceptionInternal(ex,exception, new HttpHeaders(), status, request);    }

    private ResponseEntity<Object> handlerInvalidFormatException(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

        String path = ex.getPath()
                .stream()
                .map(reference -> reference.getFieldName())
                .collect(Collectors.joining("."));
        String detail = String.format("A propiedade '%s' não faz parte da sua entidade, tente novamenete sem ela ", path);
        Exception exception = createExcptionBuilder(status, ExceptionType.MENSAGEM_BODY_IMCOMPREEMSIVEL, detail).build();

        return handleExceptionInternal(ex,exception, new HttpHeaders(), status, request);    }


    /*@ExceptionHandler(java.lang.Exception.class)
    public ResponseEntity<Object> handleUncaught(java.lang.Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionType problemType = ExceptionType.ERRO_NO_SISTEMA;
        String detail = "Ocorreu um erro interno inesperado no sistema. "
                + "Tente novamente e se o problema persistir, entre em contato "
                + "com o administrador do sistema.";

        // Importante colocar o printStackTrace (pelo menos por enquanto, que não estamos
        // fazendo logging) para mostrar a stacktrace no console
        // Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam importantes
        // para você durante, especialmente na fase de desenvolvimento
        ex.printStackTrace();

        Exception problem = createExcptionBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }*/


}
