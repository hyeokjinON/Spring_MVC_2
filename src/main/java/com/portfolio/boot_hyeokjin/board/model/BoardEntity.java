package com.portfolio.boot_hyeokjin.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/*
*
* DB에서 select문의 결과로 반환될 Entity
* - @Table : 객체를 DB 테이블처럼 인식시키는 JPA 어노테이션
- @Id, @GeneratedValue : DB에서 PK라고 생각하면 된다.
* */
@Entity
@Getter
@Setter
@ToString
@Table(name = "BoardEntity")
public class BoardEntity {
    @Id
    @GeneratedValue
    Long seq;
    String content;
    String username;
    String title;
}