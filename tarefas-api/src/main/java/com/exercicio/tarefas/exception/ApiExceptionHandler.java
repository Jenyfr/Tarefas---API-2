package com.exercicio.tarefas.exception;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.exercicio.tarefas.dto.ErroResponse;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(TarefaNaoEncontradaException.class)
    public ResponseEntity<ErroResponse> tratarTarefaNaoEncontrada(TarefaNaoEncontradaException exception,
            HttpServletRequest request) {
        ErroResponse resposta = criarErro(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getRequestURI(),
                null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarErroDeValidacao(MethodArgumentNotValidException exception,
            HttpServletRequest request) {
        Map<String, String> campos = new HashMap<>();

        exception.getBindingResult().getFieldErrors()
                .forEach(erro -> campos.put(erro.getField(), erro.getDefaultMessage()));

        ErroResponse resposta = criarErro(
                HttpStatus.BAD_REQUEST,
                "Existem campos invalidos na requisicao.",
                request.getRequestURI(),
                campos);

        return ResponseEntity.badRequest().body(resposta);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErroResponse> tratarErroDeParametro(ConstraintViolationException exception,
            HttpServletRequest request) {
        ErroResponse resposta = criarErro(
                HttpStatus.BAD_REQUEST,
                "Parametro invalido: " + exception.getMessage(),
                request.getRequestURI(),
                null);

        return ResponseEntity.badRequest().body(resposta);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErroResponse> tratarErroDeValidacaoDeMetodo(HandlerMethodValidationException exception,
            HttpServletRequest request) {
        ErroResponse resposta = criarErro(
                HttpStatus.BAD_REQUEST,
                "Existem parametros invalidos na requisicao.",
                request.getRequestURI(),
                null);

        return ResponseEntity.badRequest().body(resposta);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResponse> tratarTipoInvalido(MethodArgumentTypeMismatchException exception,
            HttpServletRequest request) {
        ErroResponse resposta = criarErro(
                HttpStatus.BAD_REQUEST,
                "O parametro '" + exception.getName() + "' possui formato invalido.",
                request.getRequestURI(),
                null);

        return ResponseEntity.badRequest().body(resposta);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponse> tratarJsonInvalido(HttpMessageNotReadableException exception,
            HttpServletRequest request) {
        ErroResponse resposta = criarErro(
                HttpStatus.BAD_REQUEST,
                "O corpo da requisicao esta invalido ou possui valores nao aceitos.",
                request.getRequestURI(),
                null);

        return ResponseEntity.badRequest().body(resposta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> tratarErroInterno(Exception exception, HttpServletRequest request) {
        ErroResponse resposta = criarErro(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro inesperado no servidor.",
                request.getRequestURI(),
                null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }

    private ErroResponse criarErro(HttpStatus status, String mensagem, String path, Map<String, String> campos) {
        return new ErroResponse(status.value(), status.getReasonPhrase(), mensagem, path, campos);
    }
}
