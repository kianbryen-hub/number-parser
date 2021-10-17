package com.kb.numberparser.rest.controller;

import com.kb.numberparser.rest.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class NumberParserRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testSuccess()
            throws Exception {
        mvc.perform(get("/parse")
                        .param("dialledNumber", "07277822334")
                        .param("userNumber", "+447866866886"))
                .andExpect(status().isOk())
                .andExpect(content().string("+447277822334"));
    }

}