package com.portfolio.boot_hyeokjin.board.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
*
* 컨트롤러에서 파라미터로 받는 Param,
* - @Min(0) : 최소값을 0으로 설정한다는 것.
- @NotEmpty : null과 "" 빈문자열 체크를 하고 있습니다.
* */
@Getter
@Setter
@ToString
@Builder
public class BoardParam {

    Long seq;

    String content;

    String username;

    String title;
}