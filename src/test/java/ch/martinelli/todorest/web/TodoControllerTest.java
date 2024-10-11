package ch.martinelli.todorest.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createAndCompleteTodo() throws Exception {
        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "id": 1,
                                    "text": "Test"
                                }"""))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{"id":1,"text":"Test","completed":false}]"""));

        mockMvc.perform(delete("/todos/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/todos/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"id":1,"text":"Test","completed":true}"""));
    }
}