package ch.martinelli.todorest.repository;

import ch.martinelli.todorest.web.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByCompleted(boolean completed);
}
