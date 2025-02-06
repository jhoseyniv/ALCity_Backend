package com.alcity.api;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class JourneyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void findOne() throws Exception {
        ResultActions result = mockMvc.perform(get("/base/client-type/id/{id}", 5));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.label").value("mobile"))
                .andExpect(jsonPath("$.value").value("mobile"))
                .andExpect(jsonPath("$.version").value("1"));
        //.andExpect(jsonPath("$.created").value(now))
        //.andExpect(jsonPath("$.updated").value("mobile"));
    }
}
