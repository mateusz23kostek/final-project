package utils;

import com.github.javafaker.Faker;

public class RandomUser {
    public String firstName;
    public String lastName;
    public String password = "1qaz!QAZ";
    public String address1;
    public String city;
    public String state;
    public String postcode;
    public String phone;
    public String email;

    private String userNamez = "";


    public RandomUser() {
        Faker faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.address1 = faker.address().streetAddress();
        this.city = faker.address().city();
        this.state = faker.address().state();
        this.postcode = String.valueOf(faker.random().nextInt(11111, 99999));
        this.phone = String.valueOf(faker.phoneNumber().cellPhone());
        this.email = this.firstName + this.lastName + faker.random().nextInt(100000) + "@wp.pl";
    }

    @Override
    public String toString() {
        return "RandomUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", address1='" + address1 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postcode='" + postcode + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
