package com.playground.GroupBy;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

@Slf4j
class GroupByTest {

    @FunctionalInterface
    interface GroupExtractor {
        Character getGroupByKey(Person person);

        static String getGroupKeyByCity(Person person) {
            return person.getCity();
        }
        static Character getGroupKeyByFirstLetterOfName(Person person) {
            return person.getName().charAt(0);
        }
    }

    private static <T> void printResults(Map<T, List<Person>> result) {
        result.forEach((city, value) -> {
            log.info("GROUP={}", city);
            value.forEach(person -> log.info("Person={}", person));
        });
    }

    @Getter
    @ToString
    static class Person {
        private final String name;
        private final int age;
        private final String city;

        public Person(String name, int age, String city) {
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
        Map<Integer, List<Person>> result = GroupBy.groupBy(people, Person::getAge);
        printResults(result);
    }

    @Test
    void groupByCity() {
        Map<String, List<Person>> result = GroupBy.groupBy(people, Person::getCity);
        printResults(result);
    }

    @Test
    void groupByLambda() {
        Map<Character, List<Person>> result = GroupBy.groupBy(people, person -> person.getCity().charAt(0));
        printResults(result);
    }

    @Test
    void groupByLambda2() {
        GroupExtractor extractor = person -> person.getCity().charAt(0);
        Map<Character, List<Person>> result = GroupBy.groupBy(people, extractor::getGroupByKey);
        printResults(result);
    }

    @Test
    void groupByLambda3() {
        GroupExtractor extractor = person -> person.getName().charAt(0);
        Map<Character, List<Person>> result = GroupBy.groupBy(people, extractor::getGroupByKey);
        printResults(result);
    }

    @Test
    void groupByLambda4() {
        Map<String, List<Person>> result = GroupBy.groupBy(people, GroupExtractor::getGroupKeyByCity);
        printResults(result);
    }

    @Test
    void groupByLambda5() {
        Map<Character, List<Person>> result = GroupBy.groupBy(people, GroupExtractor::getGroupKeyByFirstLetterOfName);
        printResults(result);
    }
}