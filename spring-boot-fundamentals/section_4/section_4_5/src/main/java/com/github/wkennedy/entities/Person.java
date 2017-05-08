package com.github.wkennedy.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Document
public class Person implements Serializable {

    @Id
    private String id;

    private String lastName;
    private String firstName;

    @Transient
    private Long timeCreated;

    public Person() {
        timeCreated = System.currentTimeMillis();
    }

    public Person(String lastName, String firstName) {
        this.id = UUID.randomUUID().toString();
        this.lastName = lastName;
        this.firstName = firstName;
        timeCreated = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Transient
    public Long getTimeCreated() {
        return timeCreated;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", timeCreated=" + timeCreated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!id.equals(person.id)) return false;
        if (!lastName.equals(person.lastName)) return false;
        return firstName.equals(person.firstName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        return result;
    }
}
