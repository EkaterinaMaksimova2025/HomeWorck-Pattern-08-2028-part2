package ru.netology.testmode.data;

import static ru.netology.testmode.data.ApiHelper.sendRequest;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import jdk.jshell.Snippet;
import lombok.Value;
import lombok.val;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {

    private static final Faker FAKER = new Faker(new Locale("en"));

    private DataGenerator() {
    }

    public static String getRandomLogin() {
        return FAKER.name().username();
    }

    public static String getRandomPassword() {
        return FAKER.internet().password();
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationDto getUser(String status) {
            return new RegistrationDto(getRandomLogin(), getRandomPassword(), status);
        }

        public static RegistrationDto getRegisteredUser(String status) {
            var user = getUser(status);
            sendRequest(user);
            return user;
        }

    }

    @Value
    public static class RegistrationDto {
        String login;
        String password;
        String status;
    }


}
