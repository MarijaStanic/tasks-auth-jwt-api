package stanic.marija.tasks.controller;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import stanic.marija.tasks.model.Todo;
import stanic.marija.tasks.service.ToDoService;
import stanic.marija.tasks.service.list.TodoHardcodedService;

@CrossOrigin()
@RestController
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private ToDoService todoService;

	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping()
	public ResponseEntity<Set<Todo>> getAllTodos() {
		return ResponseEntity.ok(todoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Todo> getTodo( @PathVariable long id) {
		return ResponseEntity.ok(todoService.findById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable long id) {
		todoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable long id,
			@RequestBody Todo todo) {
		Todo updatedTodo = this.todoService.save(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Todo> saveTodo(@PathVariable String username, @RequestBody Todo todo) {
		Todo createddTodo = this.todoService.save(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createddTodo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
