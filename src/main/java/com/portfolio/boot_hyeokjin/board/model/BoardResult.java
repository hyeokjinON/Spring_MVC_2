package com.portfolio.boot_hyeokjin.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
* ResponseEntity Json형태로 반환될 Result 객체로 구분
*- 반환타입 클래스. ResponseEntity.ok(여기); 에 들어갈 반환타입.
- 만일 정교한 매핑이 필요하다면 모두 정제하여 반환될 값만 딱 들어있을 객체
* */
@Getter
@Setter
@ToString
public class BoardResult {
    Long seq;
    String content;
    String username;
    String title;
}