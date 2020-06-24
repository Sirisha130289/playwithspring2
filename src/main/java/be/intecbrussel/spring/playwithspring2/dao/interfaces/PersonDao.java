package be.intecbrussel.spring.playwithspring2.dao.interfaces;

import be.intecbrussel.spring.playwithspring2.model.Person;

import java.util.ArrayList;
import java.util.List;

public interface PersonDao {

    List<Person> allPersons = new ArrayList<Person>();

    public boolean createPerson(Person person);

    public Person readPerson(int id);

    public boolean updatePerson(Person person);

    public boolean deletePerson(Person person);

    public List<Person> getAllPersons();
}
