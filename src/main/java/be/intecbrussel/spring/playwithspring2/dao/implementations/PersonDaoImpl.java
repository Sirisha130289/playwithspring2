package be.intecbrussel.spring.playwithspring2.dao.implementations;

import be.intecbrussel.spring.playwithspring2.model.Person;
import be.intecbrussel.spring.playwithspring2.dao.interfaces.PersonDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonDaoImpl implements PersonDao {

    @Override
    public boolean createPerson(Person person) {
        if (PersonDao.allPersons.contains(person)) {
            return false;
        } else {
            PersonDao.allPersons.add(person);
            return true;
        }
    }

    @Override
    public Person readPerson(int id) {
        Optional<Person> personOptional = PersonDao.allPersons.stream().filter(person -> person.getId() == id)
                .findAny();

        if (personOptional.isPresent()) {
            return personOptional.get();
        }

        return null;
    }

    @Override
    public boolean updatePerson(Person person) {
        if (PersonDao.allPersons.contains(person)) {
            person.setId(4);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePerson(Person person) {
        if (PersonDao.allPersons.contains(person)) {
            PersonDao.allPersons.remove(person);
            return true;
        }
        return false;
    }

    @Override
    public List<Person> getAllPersons() {
        return PersonDao.allPersons;
    }
}
