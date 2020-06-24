package be.intecbrussel.spring.playwithspring2.dao.implementations;

import be.intecbrussel.spring.playwithspring2.model.Person;
import be.intecbrussel.spring.playwithspring2.dao.interfaces.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PersonDaoJDBCImpl implements PersonDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String SQL_READ = "SELECT * FROM person WHERE id=?";
    private static final String SQL_INSERT = "INSERT INTO person VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE person SET first_name=?, WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM person WHERE id=?";

    @Override
    public boolean createPerson(Person person) {
        Person person1 = readPerson(person.getId());
        if (person1 == null) {
            int executed = jdbcTemplate.update(SQL_INSERT,
                    person.getId(), person.getFirstName(),
                    person.getLastName(), person.getDateOfBirth());
            if (executed != 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Person readPerson(int id) {
        Map<String, Object> map = null;
        try {
            map = jdbcTemplate.queryForMap(SQL_READ, id);
        } catch (Exception e) {
            System.out.println("No records for id : " + id);
        }
        Person person = null;
        if (map != null) {
            person = new Person();
            person.setId(id);
            person.setFirstName((String) map.get("first_name"));
            person.setLastName((String) map.get("last_name"));
            person.setDateOfBirth( ((Date)map.get("date_of_birth")).toLocalDate());
        }
        return person;
    }

    @Override
    public boolean updatePerson(Person person) {
        Person person1 = readPerson(person.getId());
        if (person1 != null) {
            int executed = jdbcTemplate.update(SQL_UPDATE, person.getFirstName(), person.getId());
            if (executed != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletePerson(Person person) {
        Person person1 = readPerson(person.getId());
        if (person1 != null) {
            int executed = jdbcTemplate.update(SQL_DELETE, person.getId());
            if (executed != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Person> getAllPersons() {
        String sqlAllPersons =
                " SELECT * FROM person";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlAllPersons);
        List<Person> personList = new ArrayList<>();
        list.forEach(personmap -> {
            Person person = new Person();
            person.setId(Math.toIntExact((Long) personmap.get("id")));
            person.setFirstName((String) personmap.get("first_name"));
            person.setLastName((String) personmap.get("last_name"));
            person.setDateOfBirth( ((Date)personmap.get("date_of_birth")).toLocalDate());
            personList.add(person);

        });
        return personList;
    }
}
