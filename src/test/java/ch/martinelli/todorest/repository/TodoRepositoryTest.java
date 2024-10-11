package ch.martinelli.todorest.repository;

import ch.martinelli.todorest.web.Todo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void findAllByCompleted() {
        List<Todo> allByCompleted = todoRepository.findAllByCompleted(true);

        Assertions.assertThat(allByCompleted).isEmpty();
    }
}