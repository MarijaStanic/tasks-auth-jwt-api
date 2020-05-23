package stanic.marija.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import stanic.marija.tasks.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByUsername(String username);

  User findByUsername(String username);

}
