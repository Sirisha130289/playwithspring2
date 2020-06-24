package be.intecbrussel.spring.playwithspring2.service.interfaces;

import be.intecbrussel.spring.playwithspring2.model.Person;

import java.util.List;

public interface PersonService {


    public Person getPerson(int id);

    public void addPerson(Person person);

    public void addPerson(List<Person> personList);


    public List<Person> getAllPersons();
}
