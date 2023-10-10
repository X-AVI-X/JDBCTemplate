package assignment.jdbctemplate.repository;

import assignment.jdbctemplate.entity.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonRepository {

    Optional<Person> getById(Integer id);

    List<Person> getAll();

    void add(Person person);

    int delete(Long id);


}
