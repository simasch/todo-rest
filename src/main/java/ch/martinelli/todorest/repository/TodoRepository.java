package ch.martinelli.todorest.repository;

import ch.martinelli.todorest.web.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("select t from Todo t where t.completed = :completed")
    List<Todo> findAllByCompleted(boolean completed);
}
