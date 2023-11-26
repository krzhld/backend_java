package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonInMemoryDatabaseReadWriteLock implements PersonDatabase {
    private final Map<Integer, Person> personById;
    private final Map<String, List<Person>> personsByName;
    private final Map<String, List<Person>> personsByAddress;
    private final Map<String, List<Person>> personsByPhoneNumber;
    private final ReadWriteLock readWriteLock;

    public PersonInMemoryDatabaseReadWriteLock() {
        personById = new HashMap<>();
        personsByName = new HashMap<>();
        personsByAddress = new HashMap<>();
        personsByPhoneNumber = new HashMap<>();
        readWriteLock = new ReentrantReadWriteLock();
    }

    @Override
    public void add(Person person) {
        readWriteLock.writeLock().lock();
        try {
            personById.putIfAbsent(person.id(), person);
            personsByName.computeIfAbsent(person.name(), v -> new ArrayList<>()).add(person);
            personsByAddress.computeIfAbsent(person.address(), v -> new ArrayList<>()).add(person);
            personsByPhoneNumber.computeIfAbsent(person.phoneNumber(), v -> new ArrayList<>()).add(person);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        readWriteLock.writeLock().lock();
        try {
            Person person = personById.remove(id);
            personsByName.getOrDefault(person.name(), new ArrayList<>()).remove(person);
            personsByAddress.getOrDefault(person.address(), new ArrayList<>()).remove(person);
            personsByPhoneNumber.getOrDefault(person.phoneNumber(), new ArrayList<>()).remove(person);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Person findById(int id) {
        readWriteLock.readLock().lock();
        try {
            return personById.get(id);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        readWriteLock.readLock().lock();
        try {
            return personsByName.get(name);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        readWriteLock.readLock().lock();
        try {
            return personsByAddress.get(address);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        readWriteLock.readLock().lock();
        try {
            return personsByPhoneNumber.get(phone);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
