package com.github.wkennedy.entities;

import java.io.Serializable;

public class Person implements Serializable {

    private String id;
    private String lastName;
    private String firstName;

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

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
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
