package stanic.marija.tasks.service.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import stanic.marija.tasks.model.Todo;
import stanic.marija.tasks.repository.ToDoRepository;
import stanic.marija.tasks.service.ToDoService;

@Profile("springdatajpa")
@Service
public class ToDoSDJpaService implements ToDoService {

	@Autowired
	ToDoRepository toDoRepository;

	@Override
	public Set<Todo> findAll() {
		Set<Todo> todos = new HashSet<>();
		toDoRepository.findAll().forEach(todos::add);
		return todos;
	}

	@Override
	public Todo findById(Long id) {
		return toDoRepository.findById(id).orElse(null);
	}

	@Override
	public Todo save(Todo todo) {
		return toDoRepository.save(todo);
	}

	@Override
	public void deleteById(Long id) {
		toDoRepository.deleteById(id);
	}

}
