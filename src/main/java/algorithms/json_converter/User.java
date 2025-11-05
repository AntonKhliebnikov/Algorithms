package algorithms.json_converter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class User {
    private String name;
    private int age;
    private Address address;
    private Sex sex;
    private BigDecimal salary;
    private String[] hobbies;
    private List<Integer> favoriteNumbers;
    private Map<String, String> socialProfiles;

    public static class Address {
        private String country;
        private String city;

        public Address(String country, String city) {
            this.country = country;
            this.city = city;
        }
    }

    public User(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public User(String name, int age, Address address, Sex sex) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.sex = sex;
    }

    public User(String name, int age, Address address, Sex sex, BigDecimal salary) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.sex = sex;
        this.salary = salary;
    }

    public User(String name, int age, Address address, Sex sex, BigDecimal salary, String[] hobbies) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.sex = sex;
        this.salary = salary;
        this.hobbies = hobbies;
    }

    public User(String name, int age, Address address, Sex sex, BigDecimal salary, String[] hobbies, List<Integer> favoriteNumbers) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.sex = sex;
        this.salary = salary;
        this.hobbies = hobbies;
        this.favoriteNumbers = favoriteNumbers;
    }

    public User(String name, int age, Address address, Sex sex, BigDecimal salary, String[] hobbies, List<Integer> favoriteNumbers, Map<String, String> socialProfiles) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.sex = sex;
        this.salary = salary;
        this.hobbies = hobbies;
        this.favoriteNumbers = favoriteNumbers;
        this.socialProfiles = socialProfiles;
    }
}