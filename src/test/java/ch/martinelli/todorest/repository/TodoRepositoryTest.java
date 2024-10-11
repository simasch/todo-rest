package ch.martinelli.todorest.repository;

import ch.martinelli.todorest.web.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;
    
    @BeforeEach
    void setUp() {
        Todo test = new Todo(1L, "Test");
        test.setCompleted(true);
        // saveAndFlush damit das Insert Statement produziert wirdz
        todoRepository.saveAndFlush(test);
    }

    @Test
    void findAllByCompleted() {
        List<Todo> allByCompleted = todoRepository.findAllByCompleted(true);
        assertThat(allByCompleted).size().isEqualTo(1);
    }
}