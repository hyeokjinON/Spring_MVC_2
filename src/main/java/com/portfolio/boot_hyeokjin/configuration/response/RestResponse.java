package com.portfolio.boot_hyeokjin.configuration.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* - response 구조를 커스텀할 클래스들 생성.
   1. RestResponse : 200 코드를 반환할 때 사용
* */
/*
code 는 200, 400과 같은 HttpStatus값이 들어가고, message는 반환이나 에러내용이 들어갑니다.
result는 json으로 반환될 객체입니다.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse<T> {
    private int code;
    private String message;
    private T result;
}
