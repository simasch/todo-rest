package ch.martinelli.todorest.web;

import ch.martinelli.todorest.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("todos")
@RestController
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    List<Todo> getAll(@RequestParam(required = false) Boolean complete) {
        if (complete == null) {
            return todoRepository.findAll();
        } else {
            return todoRepository.findAllByCompleted(complete);
        }
    }

    @GetMapping("{id}")
    ResponseEntity<Todo> getOne(@PathVariable Long id) {
        Optional<Todo> any = todoRepository.findById(id);

        return any.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createTodo(@RequestBody Todo todo) {
        todoRepository.save(todo);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        todoRepository.findById(id)
                .ifPresent(todo -> {
                            todo.setCompleted(true);
                            todoRepository.save(todo);
                        }
                );
    }
}
