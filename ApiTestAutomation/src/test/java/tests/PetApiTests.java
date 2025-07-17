package tests;

import helpers.HttpHelper;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class PetApiTests {
    String basePath = "/pet";
    Response response;

    @Test
    public void uploadImageToPet() {
        String petId = "47854785";
        String path = basePath + "/" + petId + "/uploadImage";

        File imageData = new File("src/main/java/files/images/cat.png");

        response = HttpHelper.postRequestWithMultipart(path, imageData);
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertTrue(response.asPrettyString().contains("File uploaded to ./cat.png"));
    }

    @Test
    public void uploadToPetWithoutImage() {
        String petId = "47854785";
        String path = basePath + "/" + petId + "/uploadImage";

        response = HttpHelper.postRequestWithoutBody(path);
        
        assertEquals(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, response.statusCode());
    }

    @Test
    public void uploadImageToNonExistentPetId() {
        String petId = "47854785";
        String path = basePath + "/" + petId + "/uploadImage";

        File imageData = new File("src/main/java/files/images/cat.png");

        response = HttpHelper.postRequestWithMultipart(path, imageData);
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    @Test
    public void addNewPet() {
        File jsonData = new File("src/main/java/files/jsonFiles/newPet.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        
        String id = response.path("id").toString();
        String name = response.path("name").toString();
        String status = response.path("status").toString();

        assertEquals("47854785", id);
        assertEquals("spaniel", name);
        assertEquals("available", status);
    }

    @Test
    public void addNewPetWithWrongIdValue() {
        File jsonData = new File("src/main/java/files/jsonFiles/newPetWithWrongIdValue.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
        
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.statusCode());
    }

    @Test
    public void addNewPetWithWrongNameValue() {
        File jsonData = new File("src/main/java/files/jsonFiles/newPetWithWrongNameValue.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    @Test
    public void addNewPetWithWrongStatusValue() {
        File jsonData = new File("src/main/java/files/jsonFiles/newPetWithWrongNameValue.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    @Test
    public void addNewPetWithNonAlphanumericChars() {
        File jsonData = new File("src/main/java/files/jsonFiles/newPetWithNonAlphaNumericChars.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
        
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode());
    }

    @Test
    public void updateExistingPet() {
        File jsonData = new File("src/main/java/files/jsonFiles/existingPet.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        
        String id = response.path("id").toString();
        String name = response.path("name").toString();
        String status = response.path("status").toString();

        assertEquals("47854785", id);
        assertEquals("ronnie", name);
        assertEquals("pending", status);
    }

    @Test
    public void updateExistingPetWithWrongIdValue() {
        File jsonData = new File("src/main/java/files/jsonFiles/existingPetWithWrongIdValue.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
        
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.statusCode());
    }

    @Test
    public void updateExistingPetWithWrongNameValue() {
        File jsonData = new File("src/main/java/files/jsonFiles/existingPetWithWrongNameValue.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    @Test
    public void updateExistingPetWithWrongStatusValue() {
        File jsonData = new File("src/main/java/files/jsonFiles/existingPetWithWrongStatusValue.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    @Test
    public void updateExistingPetWithNonAlphanumericChars() {
        File jsonData = new File("src/main/java/files/jsonFiles/existingPetWithWrongNameValue.json");

        response = HttpHelper.postRequestFile(basePath, jsonData);
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    @Test
    public void findPetsByStatusAvailable() {
        String path = basePath + "/findByStatus";

        response = HttpHelper.getRequestWithQueryParam(path, "status", "available");
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertFalse(response.asPrettyString().contains("pending") || response.asPrettyString().contains("sold"));
        assertTrue(response.asPrettyString().contains("available"));
    }

    @Test
    public void findPetsByStatusPending() {
        String path = basePath + "/findByStatus";

        response = HttpHelper.getRequestWithQueryParam(path, "status", "pending");
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertFalse(response.asPrettyString().contains("available") || response.asPrettyString().contains("sold"));
        assertTrue(response.asPrettyString().contains("pending"));
    }

    @Test
    public void findPetsByStatusSold() {
        String path = basePath + "/findByStatus";

        response = HttpHelper.getRequestWithQueryParam(path, "status", "sold");
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertFalse(response.asPrettyString().contains("available") || response.asPrettyString().contains("pending"));
        assertTrue(response.asPrettyString().contains("sold"));
    }

    @Test
    public void findPetsWithWrongStatus() {
        String path = basePath + "/findByStatus";

        response = HttpHelper.getRequestWithQueryParam(path, "status", "test100!");
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    @Test
    public void findPetById() {
        String petId = "47854785";
        String path = basePath + "/" + petId;

        response = HttpHelper.getRequest(path);
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        
        String id = response.path("id").toString();
        assertEquals("47854785", id);
    }

    // Update pet with form data tests
    @Test
    public void updatePetWithFormData() {
        String petId = "47854785";
        String path = basePath + "/" + petId;

        response = HttpHelper.postRequestWithFormParams(path, "name", "jonie", "status", "sold");
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    @Test
    public void updatePetWithWrongFormData() {
        String petId = "47854785";
        String path = basePath + "/" + petId;

        response = HttpHelper.postRequestWithFormParams(path, "name", "1000", "status", "1000");
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    // Delete pet test
    @Test
    public void deletePet() {
        String petId = "47854785";
        String path = basePath + "/" + petId;

        response = HttpHelper.deleteRequest(path);
        
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        
        String message = response.path("message").toString();
        assertEquals("47854785", message);
    }
}
