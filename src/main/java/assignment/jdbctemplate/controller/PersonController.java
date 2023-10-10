package assignment.jdbctemplate.controller;

import assignment.jdbctemplate.entity.Person;
import assignment.jdbctemplate.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private IPersonRepository personRepository;

    @GetMapping("/get/all")
    public ResponseEntity<List<Person>> getAllPerson() {
        List<Person> PersonList = personRepository.getAll();
        return new ResponseEntity<>(PersonList, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
        Person person = personRepository.getById(id).get();
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addPerson(@RequestBody Person person) {
        personRepository.add(person);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deletePerson (@PathVariable Long id){
        if (personRepository.delete(id) > 0)
            return new ResponseEntity<>(null, HttpStatus.OK);
        else return new ResponseEntity<>("Not found! Couldn't delete.", HttpStatus.NOT_FOUND);
    }
}
