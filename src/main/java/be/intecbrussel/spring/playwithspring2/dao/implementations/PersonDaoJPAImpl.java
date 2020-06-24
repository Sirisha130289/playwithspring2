package be.intecbrussel.spring.playwithspring2.dao.implementations;

import be.intecbrussel.spring.playwithspring2.model.Person;
import be.intecbrussel.spring.playwithspring2.dao.interfaces.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@Transactional
@Primary
public class PersonDaoJPAImpl implements PersonDao {


    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {

        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public boolean createPerson(Person person) {

        entityManager.persist(person);
        return true;
    }

    @Override
    public Person readPerson(int id) {

        return entityManager.find(Person.class, id);
    }

    @Override
    public boolean updatePerson(Person person) {
        person = entityManager.merge(person);
        return true;
    }

    @Override
    public boolean deletePerson(Person person) {
        entityManager.remove(person);
        return true;
    }

    @Override
    public List<Person> getAllPersons() {
        return entityManager.createQuery("SELECT p FROM Person p").getResultList();
    }
}
