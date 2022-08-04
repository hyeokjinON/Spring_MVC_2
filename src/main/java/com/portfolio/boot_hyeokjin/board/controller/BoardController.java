package com.portfolio.boot_hyeokjin.board.controller;

import com.portfolio.boot_hyeokjin.board.model.BoardParam;
import com.portfolio.boot_hyeokjin.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/*
*
- @RequestBody : request객체의 Body에 JSON 형태로 넘어온 데이터를 BoardParam 객체에 매핑하는 것.
Get 방식은 request Body가 없기 때문에 안됨.
- @Valid : 유효성체크해주는 어노테이션, BoardParam에 존재하는 @NotEmpty, @NotNull 따위의 어노테이션을 체크하기 위해서 사용.
- @PathVariable : 예를 들어, delete 메소드에서 /api/boards/3 이라는 URI값으로 DELETE 방식으로 데이터가 넘어온다면 3이라는 값이 {seq}에 매핑되고 Long seq에 대입된다.
*
* <ResponseEntity 주석처리 후 Object사용시>
* - @Validated 어노테이션은 @Min(1) 어노테이션을 사용하기 위해 클래스단에 먼저 선언해두는 어노테이션이다.
* Min 어노테이션을 설정해둔 이유는 @PathVariable 어노테이션으로 받은 seq값이 0 이하일 수는 없기 때문이다.
- 또한 한정적인 형태인 ResponseEntity 객체를 사용하지 않고 직접 정의한 객체를 Response로 전달하기 위해 Object 타입으로 리턴하게 하였음.
 */
@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(
            value = "/all",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object getAll() throws Exception{
        return boardService.getBoard();
    }

    @GetMapping(
            value = "/{seq}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object get(@PathVariable("seq") @Min(1) Long seq) throws Exception{
        return boardService.getBoard(seq);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object add(@RequestBody @Valid BoardParam param) throws Exception{
        boardService.add(param);
        return null;
    }

    @PutMapping(value = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object edit(@RequestBody @Valid BoardParam param,
                       @PathVariable("seq") @Min(1) Long seq) throws Exception{
        param.setSeq(seq);
        boardService.edit(param);
        return null;
    }

    @DeleteMapping(value = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object delete(@PathVariable("seq") @Min(1) Long seq) throws Exception{
        boardService.delete(seq);
        return null;
    }

    /*@GetMapping(
            value = "/all",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(){
        return ResponseEntity.ok(boardService.getBoard());
    }

    @GetMapping(
            value = "/{seq}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(@PathVariable("seq") Long seq){

        return ResponseEntity.ok(boardService.getBoard(seq));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody BoardParam param){
        boardService.add(param);
        return ResponseEntity.ok(null);
    }

    @PutMapping(value = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity edit(@RequestBody @NotNull BoardParam param,
                               @PathVariable("seq") Long seq){
        param.setSeq(seq);
        boardService.edit(param);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping(value = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable("seq") Long seq){
        boardService.delete(seq);
        return ResponseEntity.ok(null);
    }*/
}
