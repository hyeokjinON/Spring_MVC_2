package com.portfolio.boot_hyeokjin.configuration.aspect;


import com.portfolio.boot_hyeokjin.configuration.response.RestResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/*
*
 - RestControllerAspect 클래스
   : RestController 역할의 클래스를 감싸는 역할.
*/
/*
*
* - @Aspect 어노테이션은 해당 클래스에서 AOP를 활용할 수 있도록 합니다.
- @Around 어노테이션은 메서드 앞 뒤에 컨트롤러 메소드를 둔다고 보면 되겠습니다.
- execution 안에있 정규식에 해당하는 메소드들을  잡게됩니다.
- 모든 메소드의 시작은 restResponseHandler 라는 메소드에서 RestResponse 생성자에서 joinPoint.proceed() 메소드를 실행하고 컨트롤러로 향하게 됩니다.
- 모든 로직이 예외없이 실행되면 ReseResponse 객체를 리턴하게 됩니다.*/
@Component
@Aspect
public class RestControllerAspect {

    @Around("execution(* co.worker.board.*.controller.*.*(..))")
    public RestResponse<Object> restResponseHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        return new RestResponse<>(HttpStatus.OK.value(), "success", joinPoint.proceed());
    }
}