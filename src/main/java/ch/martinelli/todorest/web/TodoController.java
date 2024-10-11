package ch.martinelli.todorest.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("todos")
@RestController
public class TodoController {

    private final List<Todo> todos;

    public TodoController() {
        this.todos = new ArrayList<>();
        this.todos.add(new Todo(1L, "Einkaufen"));
    }

    @GetMapping
    List<Todo> getAll() {
        return todos;
    }

    @GetMapping("{id}")
    ResponseEntity<Todo> getOne(@PathVariable Long id) {
        Optional<Todo> any = todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findAny();

        return any.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createTodo(@RequestBody Todo todo) {
        todos.add(todo);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .forEach(todo -> todo.setCompleted(true));
    }
}
