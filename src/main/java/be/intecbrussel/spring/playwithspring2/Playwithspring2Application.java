package be.intecbrussel.spring.playwithspring2;

import be.intecbrussel.spring.playwithspring2.model.Person;
import be.intecbrussel.spring.playwithspring2.service.implementations.PersonServiceImpl;
import be.intecbrussel.spring.playwithspring2.service.implementations.PersonServiceJDBCImpl;
import be.intecbrussel.spring.playwithspring2.service.implementations.PersonServiceJPAImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Playwithspring2Application {

    public static void main(String[] args) {


        Person person1 = new Person();
        person1.setFirstName("Tom");
        person1.setLastName("Cruise");
        person1.setDateOfBirth(LocalDate.now());
        person1.setId(6);


        Person person2 = new Person();
        person2.setFirstName("Michael");
        person2.setLastName("Jackson");
        person2.setDateOfBirth(LocalDate.now());
        person2.setId(7);


        Person person3 = new Person();
        person3.setFirstName("John");
        person3.setLastName("Williams");
        person3.setDateOfBirth(LocalDate.now());
        person3.setId(8);
        try (
                ConfigurableApplicationContext applicationContext =
                        SpringApplication.run(Playwithspring2Application.class, args)) {
            applicationContext.getBean(PersonServiceImpl.class).addPerson(person1);
            List<Person> personList = new ArrayList<>();
            personList.add(person2);
            personList.add(person3);
            applicationContext.getBean(PersonServiceImpl.class).addPerson(personList);
            applicationContext.getBean(PersonServiceImpl.class).getAllPersons().forEach(System.out::println);
            System.out.println(applicationContext.getBean(PersonServiceImpl.class).getPerson(2));
            System.out.println("***JDBC Impl***");
            PersonServiceJDBCImpl personJDBC = (PersonServiceJDBCImpl) applicationContext.getBean("personServiceJDBCImpl");
            System.out.println("Adding the person");
            personJDBC.addPerson(person1);
            System.out.println("Adding list of persons");
            personJDBC.addPerson(personList);
            System.out.println("getting all persons");
            personJDBC.getAllPersons().forEach(System.out::println);
            System.out.println("Fetching person with id 5");
            System.out.println(personJDBC.getPerson(5));
            System.out.println("***JPA Impl***");
            PersonServiceJPAImpl personServiceJPA = (PersonServiceJPAImpl) applicationContext.getBean("personServiceJPAImpl");
            System.out.println("Adding the person");
            personServiceJPA.addPerson(person1);
            System.out.println("Adding list of persons");
            personServiceJPA.addPerson(personList);
            System.out.println("getting all persons");
            personServiceJPA.getAllPersons().forEach(System.out::println);
            System.out.println("Fetching person with id 5");
            System.out.println(personServiceJPA.getPerson(5));
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}

