package com.portfolio.boot_hyeokjin.configuration.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*- response 구조를 커스텀할 클래스들 생성.
   2. ErrorResponse : 에러 발생시 사용*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse{
    private Integer code;
    private String message;
}
