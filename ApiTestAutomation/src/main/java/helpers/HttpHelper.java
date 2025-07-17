package helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class HttpHelper {
    // Base URL for the Petstore API
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    
    static {
        // Configure RestAssured base URI
        RestAssured.baseURI = BASE_URL;
    }

    public static Response getRequest(String path) {
        return given().
                get(path).
                then().
                extract().response();
    }

    public static Response getRequestWithQueryParam(String path, String paramKey, String paramValue) {
        return given().
                queryParam(paramKey, paramValue).
                contentType(ContentType.JSON).
                get(path).
                then().
                extract().response();
    }

    public static Response postRequest(String path, String requestBody) {
        return given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                post(path).
                then().
                extract().response();
    }

    public static Response postRequestFile(String path, File requestBody) {
        return given().
                contentType(ContentType.JSON).
                body(requestBody).
                post(path).
                then().
                extract().response();
    }

    public static Response postRequestWithFormParams(String path, String formKey1, String formValue1, String formKey2, String formValue2) {
        return given().
                formParam(formKey1, formValue1).
                formParam(formKey2, formValue2).
                when().
                post(path).
                then().
                extract().response();
    }

    public static Response postRequestWithMultipart(String path, File formValue) {
        return given().
                contentType(ContentType.MULTIPART).
                multiPart("file", formValue).
                when().
                post(path).
                then().
                extract().response();
    }

    public static Response postRequestWithoutBody(String path) {
        return given().
                when().
                post(path).
                then().
                extract().response();
    }

    public static Response deleteRequest(String path) {
        return given().
                when().
                delete(path).
                then().
                extract().response();
    }

}