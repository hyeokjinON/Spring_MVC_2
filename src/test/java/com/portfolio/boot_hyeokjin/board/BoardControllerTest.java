package com.portfolio.boot_hyeokjin.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.boot_hyeokjin.board.model.BoardEntity;
import com.portfolio.boot_hyeokjin.board.model.BoardParam;
import com.portfolio.boot_hyeokjin.board.reposiroty.BoardRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*
*- @Before : @Test 어노테이션이 붙은 메소드가 실행되기 전에 미리 실행하여 몇몇개의 데이터를 Insert 해놓고 테스트하기 위해서 작성.
- ObjectMapper 클래스를 이용해서 build된 param 객체를 JsonString 타입으로 변형시킵니다.
  * 예를 들어, {"seq":null,"content":"추가내용","username":"추가유저","title":"추가제목"} 이런식으로 값을 넘깁니다.
- 메소드 옵션(get,post,put,delete)과 uri를 이용해서 api를 호출
*
* */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void insertBoard(){
        for(int i =0; i<10; i++){
            BoardEntity board = new BoardEntity();
            board.setContent("내용"+i);
            board.setTitle("제목"+i);
            board.setUsername("코딩하는흑구");
            boardRepository.save(board);
        }
    }

    @Test
    public void addBoard() throws Exception {
        BoardParam param = BoardParam.builder()
                .content("추가내용")
                .title("추가제목")
                .username("추가유저")
                .build();

        mockMvc.perform(post("/api/boards/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        this.getBoard();
    }

    @Test
    public void editBoard() throws Exception{
        BoardParam param = BoardParam.builder()
                .content("수정내용")
                .title("수정제목")
                .username("수정유저")
                .build();

        mockMvc.perform(put("/api/boards/3")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isOk());
        this.getBoard();
    }

    @Test
    public void getBoard() throws Exception {
        mockMvc.perform(get("/api/boards/all")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getBoardOne() throws Exception {
        mockMvc.perform(get("/api/boards/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBoardOne() throws Exception{
        mockMvc.perform(delete("/api/boards/3")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
        this.getBoard();
    }



    /*
    * - board_BadRequest_add() : 유저명 null, 게시판내용 null validation. , content "" not empty validation. / insert 테스트
    - board_BadRequest_getOne() : @Min 어노테이션으로 seq 값 validation 체크 / 1개 반환 테스트
    - board_BadRequest_edit() : 유저명 null, @Min 어노테이션 체크 / 수정 테스트
    */
    //Bad_Request 테스트
    @Test //add
    public void board_BadRequest_add() throws Exception{
        //username not null
        BoardParam param = BoardParam.builder().content("test")
                .title("test").build();

        // content not empty
        //BoardParam param = BoardParam.builder().title("title").content("").username("woo").build();

        mockMvc.perform(post("/api/boards/add")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void board_BadRequest_getOne() throws Exception{
        //0 이하인 경우 @Min 어노테이션으로 잡는지 확인.
        mockMvc.perform(get("/api/boards/-1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void board_BadRequest_edit() throws Exception {
        //username null
        //BoardParam param = BoardParam.builder().content("test").title("test").build();

        // seq min 0
        BoardParam param = BoardParam.builder().content("test")
                .title("test")
                .username("gg").build();

        mockMvc.perform(put("/api/boards/0")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}