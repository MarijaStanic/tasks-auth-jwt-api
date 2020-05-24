package stanic.marija.tasks.service.list;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import stanic.marija.tasks.model.Todo;
import stanic.marija.tasks.service.ToDoService;

@Profile({"default"})
@Service
public class TodoHardcodedService implements ToDoService {

	private static Set<Todo> todos = new HashSet<>();
	private static Long idCounter = 0L;

	static {
		todos.add(new Todo(++idCounter, "Tamara", "Learn to Dance", new Date(), false));
		todos.add(new Todo(++idCounter, "Tamara", "Learn Docker", new Date(), false));
	}

	public Set<Todo> findAll() {
		return todos;
	}

	@Override
	public void deleteById(Long id) {
		Todo todo = findById(id);
		if (todo == null)
			return;
		todos.remove(todo);
	}

	@Override
	public Todo findById(Long id) {
		return todos.stream().filter(td -> td.getId() == id).findFirst().orElse(null);
	}

	@Override
	public Todo save(Todo todo) {
		if (todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++idCounter);
			todos.add(todo);
		} else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}

}
