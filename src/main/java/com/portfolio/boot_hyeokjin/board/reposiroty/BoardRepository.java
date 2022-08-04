package com.portfolio.boot_hyeokjin.board.reposiroty;

import com.portfolio.boot_hyeokjin.board.model.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
*  JpaRepository<반환테이블객체, @ID 타입> 을 상속받는 인터페이스는 JPA 레퍼지토리로써 데이터 영속층에 해당합니다.
- 자동적으로 PK를 이용한 객체 Select나 전체 Select, 삭제, 수정을 지원하는 메소드가 생깁니다.*/
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}