package com.portfolio.boot_hyeokjin.board.service;

import com.portfolio.boot_hyeokjin.board.model.BoardEntity;
import com.portfolio.boot_hyeokjin.board.model.BoardParam;
import com.portfolio.boot_hyeokjin.board.model.BoardResult;
import com.portfolio.boot_hyeokjin.board.reposiroty.BoardRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/*
* service 클래스는 인터페이스를 클래스가 구현하는 형태가 아닌 직접 클래스가 역할을 하는 쪽으로 함.
* -> 인터페이스를 이용하여 다형성을 구현하는 형태가 아니라 일종의 루틴처럼 사용되는 경향이 있어보여서 과감히 탈피.
*
* */
@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Transactional
    public List<BoardResult> getBoard(){
        List<BoardEntity> entityList = boardRepository.findAll();
        List<BoardResult> results = entityList.stream().map(boardEntity -> {
            BoardResult boardResult = new BoardResult();
            boardResult.setContent(boardEntity.getContent());
            boardResult.setUsername(boardEntity.getUsername());
            boardResult.setTitle(boardEntity.getTitle());
            boardResult.setSeq(boardEntity.getSeq());
            return boardResult;
        }).collect(Collectors.toList());

        return results;
    }

    @Transactional
    public Object getBoard(Long seq){
        return boardRepository.findById(seq).map(boardEntity -> {
            BoardResult boardResult = new BoardResult();
            boardResult.setContent(boardEntity.getContent());
            boardResult.setUsername(boardEntity.getUsername());
            boardResult.setTitle(boardEntity.getTitle());
            boardResult.setSeq(boardEntity.getSeq());
            return boardResult;
        }).get();
    }

    @Transactional
    public void edit(@NotNull BoardParam param) {
        Optional<BoardEntity> getEntity = boardRepository.findById(param.getSeq());
        getEntity.ifPresent(entity -> {
            entity.setTitle(param.getTitle());
            entity.setContent(param.getContent());
            entity.setUsername(param.getUsername());
            boardRepository.save(entity);
        });
    }

    @Transactional
    public void add(BoardParam param) {
        BoardEntity entity = new BoardEntity();
        entity.setUsername(param.getUsername());
        entity.setContent(param.getContent());
        entity.setTitle(param.getTitle());
        boardRepository.save(entity);
    }

    @Transactional
    public void delete(Long seq) {
        boardRepository.deleteById(seq);
    }
}
