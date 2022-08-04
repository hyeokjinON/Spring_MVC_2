package com.portfolio.boot_hyeokjin.configuration.controlleradvice;


import com.portfolio.boot_hyeokjin.configuration.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/*
*  - RestControllerExceptionAdvice
   :  RestController에서 발생한 에러를 최대한 모두 잡는 역할.*/

/*
* - @ResponseStatus(HttpStatus) : 해당 리스폰스의 타입을 BAD_REQUEST(400)으로 반환하도록 합니다.
- @ExceptionHandler(class) : 해당 메소드가 잡아야할 Exception을 처리합니다.
* 서비스 로직에서 발생한 RuntimeException은 handlerRuntimeException() 메소드에서 처리하게 됩니다.
- 리턴타입은 ErrorResponse 타입이다.*/
@org.springframework.web.bind.annotation.RestControllerAdvice
@Slf4j
public class RestControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handlerRuntimeException(RuntimeException e, HttpServletRequest req){
        log.error("================= Handler RuntimeException =================");
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "RuntimeException : "+e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                HttpServletRequest req){
        log.error("================= Handler MethodArgumentNotValidException =================");
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "MethodArgumentNotValidException : "+e.getMessage()
        );
    }
}
