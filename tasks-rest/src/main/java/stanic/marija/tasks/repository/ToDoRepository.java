package stanic.marija.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import stanic.marija.tasks.model.Todo;

public interface ToDoRepository extends JpaRepository<Todo, Long> {

}
