package edu.hw7.task3;

import java.util.Objects;

public record Person(int id, String name, String address, String phoneNumber) {
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return this.id == person.id
            && Objects.equals(this.address, person.address)
            && Objects.equals(this.phoneNumber, person.phoneNumber);
    }
}
