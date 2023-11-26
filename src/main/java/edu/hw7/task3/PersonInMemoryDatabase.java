package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonInMemoryDatabase implements PersonDatabase {
    private final Map<Integer, Person> personById;
    private final Map<String, List<Person>> personsByName;
    private final Map<String, List<Person>> personsByAddress;
    private final Map<String, List<Person>> personsByPhoneNumber;

    public PersonInMemoryDatabase() {
        personById = new HashMap<>();
        personsByName = new HashMap<>();
        personsByAddress = new HashMap<>();
        personsByPhoneNumber = new HashMap<>();
    }

    @Override
    public synchronized void add(Person person) {
        personById.putIfAbsent(person.id(), person);
        personsByName.computeIfAbsent(person.name(), v -> new ArrayList<>()).add(person);
        personsByAddress.computeIfAbsent(person.address(), v -> new ArrayList<>()).add(person);
        personsByPhoneNumber.computeIfAbsent(person.phoneNumber(), v -> new ArrayList<>()).add(person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = personById.remove(id);
        personsByName.getOrDefault(person.name(), new ArrayList<>()).remove(person);
        personsByAddress.getOrDefault(person.address(), new ArrayList<>()).remove(person);
        personsByPhoneNumber.getOrDefault(person.phoneNumber(), new ArrayList<>()).remove(person);
    }

    public Person findById(int id) {
        return personById.get(id);
    }

    @Override
    public List<Person> findByName(String name) {
        return personsByName.get(name);
    }

    @Override
    public List<Person> findByAddress(String address) {
        return personsByAddress.get(address);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return personsByPhoneNumber.get(phone);
    }
}
