package com.dalton.WeatherApp.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.dalton.WeatherApp.model.Person;

public class PersonCRUDMain {
    public static void main(String[] args) {
        // Create the Hibernate configuration object
        Configuration configuration = new Configuration().configure();

        // Create a Hibernate SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Create a Hibernate Session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        Transaction transaction = session.beginTransaction();

        // Create a new Person object
        Person person = new Person();
        person.setFirstName("John");

        // Save the Person object to the database
        session.save(person);

        // Commit the transaction
        transaction.commit();

        // Close the Hibernate Session
        session.close();

        // Close the Hibernate SessionFactory
        sessionFactory.close();
    }
}

