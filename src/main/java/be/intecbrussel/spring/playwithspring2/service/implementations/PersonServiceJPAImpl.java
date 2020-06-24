package be.intecbrussel.spring.playwithspring2.service.implementations;

import be.intecbrussel.spring.playwithspring2.model.Person;
import be.intecbrussel.spring.playwithspring2.dao.implementations.PersonDaoJPAImpl;
import be.intecbrussel.spring.playwithspring2.service.interfaces.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceJPAImpl implements PersonService {
    @Autowired
    private PersonDaoJPAImpl personDao;

    @Override
    public Person getPerson(int id) {
        return personDao.readPerson(id);
    }

    @Override
    public void addPerson(Person person) {
        personDao.createPerson(person);
    }

    @Override
    public void addPerson(List<Person> personList) {
        personList.forEach(person -> personDao.createPerson(person));
    }

    @Override
    public List<Person> getAllPersons() {

        return personDao.getAllPersons();
    }
}
