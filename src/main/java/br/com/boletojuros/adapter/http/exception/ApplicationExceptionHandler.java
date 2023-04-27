package br.com.boletojuros.adapter.http.exception;


import br.com.boletojuros.core.exception.AplicationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AplicationException.class)
    public ResponseEntity<ErrorResponse> applicationException(AplicationException ex, WebRequest request){
        var response = ErrorResponse.builder()
                .mensagens(Arrays.asList(ex.getTipoExcecao().getMensagemErro()))
                .erro(ex.getTipoExcecao().toString().toLowerCase())
                .codigo(HttpStatus.BAD_REQUEST.value())
                .timeStamp(new Date())
                .path(request.getDescription(false))
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var erros = ex.getFieldErrors().stream()
                .map(item -> item.getField() + " " + item.getDefaultMessage())
                .collect(Collectors.toList());
        var response = ErrorResponse.builder()
                .mensagens(erros)
                .erro("Erro na validação DTO")
                .codigo(HttpStatus.BAD_REQUEST.value())
                .timeStamp(new Date())
                .path(request.getDescription(false))
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
