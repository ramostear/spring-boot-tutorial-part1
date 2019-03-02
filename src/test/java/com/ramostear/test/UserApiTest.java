package com.ramostear.test;

import com.ramostear.persistence.model.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * @author ramostear
 * @create-time 2019/3/3 0003-4:00
 * @modify by :
 * @since:
 */
public class UserApiTest {

    private static final String API_ROOT_URL = "http://localhost:8888/api/users";

    @Test
    public void whenGetAllUsers_thenOk(){
        final Response response = RestAssured.get(API_ROOT_URL);
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCode());
    }

    @Test
    public void whenGetUsersByUsername_thenOk(){
        final User user = createRandomUser();
        createUserAsUri(user);
        final Response response = RestAssured.get(API_ROOT_URL+"/username/"+user.getUsername());
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCode());
       Assert.assertTrue(response.as(List.class).size() >0);
    }

    @Test
    public void whenGetCreatedUserById_thenOk(){
        final User user = createRandomUser();
        final String location = createUserAsUri(user);
        System.out.println(location);
        final Response response = RestAssured.get(location);
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        Assert.assertEquals(user.getUsername(),response.jsonPath().get("username"));
    }

    @Test
    public void whenGetNotExistUserById_thenNotFound(){
        final Response response = RestAssured.get(API_ROOT_URL+"/"+RandomStringUtils.randomNumeric(4));
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatusCode());
    }


    @Test
    public void whenCreateNewUser_thenCreated(){
        final User user = createRandomUser();
        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(user)
                .post(API_ROOT_URL);
        Assert.assertEquals(HttpStatus.CREATED.value(),response.getStatusCode());
    }

    @Test
    public void whenInvalidUserByUsernameNull_thenError(){
        final User user = createRandomUser();
        user.setUsername(null);
        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(user)
                .put(API_ROOT_URL);
        Assert.assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(),response.getStatusCode());
    }

    @Test
    public void whenUpdateCreatedUser_thenUpdated(){
        final User user = createRandomUser();
        final String location = createUserAsUri(user);
        user.setId(Long.parseLong(location.split("api/users/")[1]));
        user.setUsername("newUsername");
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(user)
                .put(location);
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        response = RestAssured.get(location);
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        Assert.assertEquals("newUsername",response.jsonPath().get("username"));
    }

    @Test
    public void whenDeleteCreatedUser_thenOk(){
        final User user = createRandomUser();
        final String location = createUserAsUri(user);
        Response response = RestAssured.delete(location);
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCode());

        response = RestAssured.get(location);
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatusCode());
    }

    private User createRandomUser(){
        final User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(10));
        user.setAddress(RandomStringUtils.randomAlphabetic(15));
        return user;
    }

    private String  createUserAsUri(User user){
        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(user)
                .post(API_ROOT_URL);
        return API_ROOT_URL+"/"+response.jsonPath().get("id");
    }
}
