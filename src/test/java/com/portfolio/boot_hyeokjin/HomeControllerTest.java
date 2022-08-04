package com.portfolio.boot_hyeokjin;

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
@AutoConfigureMockMvc : MockMvc 객체를 주입받아 사용하기 위해서 자동설정해주는 어노테이션입니다.
선언되지 않았을 경우 직접 빌드해주어야 하는 객체입니다.
.param() : 파라미터를 전달하는 메소드입니다.
.andExpect() : 예상되는 상태를 넣어주는 메소드입니다.
.andDo() : 테스트 동안 실행할 부분을 넣어주는 메소드입니다. 주로 print() 메소드로 아래의 출력결과 내용을 출력합니다.*/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getHome() {
        try {
            mockMvc.perform(get("/api/home/get")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            //.params(paramMap))
                            .param("name", "woo")
                            .param("age", "19"))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}