package main.java.com.gowpar.streams2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sample {

    public static List<Person> createPeople() {
        return Arrays.asList(
                new Person("Sara", Gender.FEMALE, 20),
                new Person("Sara", Gender.FEMALE, 22),
                new Person("Bob", Gender.MALE, 20),
                new Person("Paula", Gender.FEMALE, 32),
                new Person("Paul", Gender.MALE, 32),
                new Person("Jack", Gender.MALE, 2),
                new Person("Jack", Gender.MALE, 72),
                new Person("Jill", Gender.FEMALE, 12)
        );
    }

    public static void main(String[] args) {
        List<Person> people = createPeople();

        // Get, in uppercase, the names of all female older than 18
        // Old way
        List<String> names = new ArrayList<>();
        for (Person person : people) {
            if (person.getAge() > 18) {
                names.add(person.getName().toUpperCase());
            }
        }
        System.out.println(names);

        // Streams way
        List<String> names2 =
                people
                        .stream()
                        .filter(person -> person.getAge() > 18)
                        .map(Person::getName)
                        .map(String::toUpperCase)
                        .collect(Collectors.toList());
        System.out.println(names2);

        // Get all the Males
        people.stream()
                .filter(person -> person.getGender() == Gender.MALE)
                .map(person -> new Person(person.getName().toUpperCase(),
                        person.getGender(), person.getAge()))
                .forEach(System.out::println);

        // Add all the age
        System.out.println(
                people.stream()
                        .mapToInt(Person::getAge)
                        .sum()
        );

        // Max age
        System.out.println(
                people.stream()
                        .max((person1, person2) ->
                                person1.getAge() > person2.getAge() ? 1 : -1)
        );

        // Min age
        System.out.println(
                people.stream()
                        .min((person1, person2) ->
                                person1.getAge() > person2.getAge() ? 1 : -1)
        );

        // Count those who are less than 18 years old
        System.out.println(
                people.stream()
                        .filter(person -> person.getAge() < 18)
                        .count()
        );

        List<String> names3 =
                people.stream()
                    .filter(person -> person.getAge() > 17)
                    .map(person -> person.getName().toUpperCase())
                    .collect(Collectors.toList());

        System.out.println(names3);
    }
}
