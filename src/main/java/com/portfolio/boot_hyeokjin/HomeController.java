package com.portfolio.boot_hyeokjin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*@RestController : 현재 컨트롤러가 데이터를 반환하는 REST 컨트롤러라는 것을 선언하는 것.
ResponseEntity : REST 방식을 구현하는 객체. ok() 라는 static 메소드를 이용하여 ResponseBody에 Json형식으로 데이터를 반환할 것이다.
@RequestParam(String) : String 파라미터로 들어가는 값의 파라미터명으로*/
@RestController
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping("/get")
    public ResponseEntity getHome(@RequestParam("name") String name,
                                  @RequestParam("age") String age){
        Home home = new Home();
        home.setAge(Integer.parseInt(age));
        home.setName(name);

        return ResponseEntity.ok(home);
    }
}