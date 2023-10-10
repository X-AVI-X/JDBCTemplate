package assignment.jdbctemplate.repository;

import assignment.jdbctemplate.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements IPersonRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Optional<Person> getById(Integer id) {
        String query = "select * from person where id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (resultSet, rowNum) ->
                Optional.of(
                        new Person(
                                resultSet.getLong("id"),
                                resultSet.getString("name")
                        )
                )
        );
    }

    @Override
    public List<Person> getAll() {
        String query = "select * from person";
        return jdbcTemplate
                .query(query, (resultSet, rowNum) ->
                        new Person(
                                resultSet.getLong("id"),
                                resultSet.getString("name")
                        )
                );
    }

    @Override
    public void add(Person person) {
        String query = "insert into person (id, name) values (?, ?)";
        jdbcTemplate.update(query, person.getId(), person.getName());
    }

    @Override
    public int delete(Long id) {
        String query = "DELETE FROM person WHERE id = ?;";
        int rowCount = jdbcTemplate.update(query, id);
        return rowCount;
    }

//    @Override
    public boolean exists(Long id) {
        String query = "";
        return false;
    }

}
