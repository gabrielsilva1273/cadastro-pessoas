package com.gabriel.cadastropessoas.error;



import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerAdviceHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> argumentoNaoValido(MethodArgumentNotValidException exception){
        ErroBuilder builder = new ErroBuilder();
        String tipoErro = "Erro no preenchimento de informações";
        List<Erro> listaDeErros = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> builder.tipo(tipoErro).mensagem(fieldError.getDefaultMessage()).build())
                .toList();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(listaDeErros);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> violaçãoDeRestrição(ConstraintViolationException exception){
        ErroBuilder builder = new ErroBuilder();
        String tipoErro = "Erro no preenchimento de informações";
        List<Erro> listaDeErros = exception.getConstraintViolations().stream()
                .map(constraintViolation -> builder.tipo(tipoErro).mensagem(constraintViolation.getMessage()).build())
                .toList();
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(listaDeErros);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> mensagemNaoLegivel(HttpMessageNotReadableException exception){
        ErroBuilder builder = new ErroBuilder();
        String tipoErro = "Erro no preenchimento de informações";
        Erro erro = builder.tipo(tipoErro).mensagem(exception.getMessage()).build();
        return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body(erro);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> naoExisteNadaParaExcluir(NoSuchElementException exception){
        ErroBuilder builder = new ErroBuilder();
        String tipoErro = "Erro no banco de dados";
        Erro erro = builder.tipo(tipoErro).mensagem("Não existe este elemento").build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }


}
