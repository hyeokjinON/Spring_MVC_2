package com.portfolio.boot_hyeokjin.configuration.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*
*
 - ServiceExceptionAspect
   : 서비스 로직에서 발생하는 에러를 모두 잡는 역할.
   RuntimeException으로 모두 바꿔서 리턴하도록 함.( 서비스 로직에서만 )
 */
@Component
@Aspect
public class ServiceExceptionAspect {

    @Around("execution(* co.worker.board.*.service.*.*(..))")
    public Object serviceExceptionHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            //서비스 로직 에러를 RuntimeException으로 컨트롤러에 전달.
            throw new RuntimeException(e);
        }
    }
}