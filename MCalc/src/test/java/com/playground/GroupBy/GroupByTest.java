package com.playground.GroupBy;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class GroupByTest {

    @Getter
    @ToString
    static class Person {
        private final String name;
        private final int age;
        private final String city;

        public Person (String name, int age, String city) {
            this.name = name;
            this.age = age;
            this.city = city;
        }
    }

    private static final List<Person> people = List.of(
            new Person("Alice", 25, "New York"),
            new Person("Bob", 30, "Boston"),
            new Person("Charlie", 35, "New York"),
            new Person("Diana", 25, "Chicago"),
            new Person("Eve", 30, "Boston")
    );

    @Test
    void groupByAge() {
        log.info("People={}", people);
        Map<Integer, List<Person>> result= GroupBy.groupBy(people, Person::getAge);
        log.info("Grouped by Age={}", result);
    }

    @Test
    void groupByCity() {
        Map<String, List<Person>> result = GroupBy.groupBy(people, Person::getCity);
        result.forEach((city, value) -> {
            log.info("GROUP={}", city);
            value.forEach(person -> log.info("Person={}", person));
        });
    }
}