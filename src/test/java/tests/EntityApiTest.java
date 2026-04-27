package tests;

import api.BaseApiTest;
import dto.CreateRequest;
import dto.EntityResponse;
import dto.PatchRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntityApiTest extends BaseApiTest {

    @Test
    void shouldCreateEntity() {
        CreateRequest request = CreateRequest.builder()
                .title("create title")
                .description("create description")
                .verified(true)
                .build();

        Integer id = entityClient.create(request);
        EntityResponse response = entityClient.getById(id);

        assertNotNull(response.id());
        assertEquals(id, response.id());
        assertEquals(request.getTitle(), response.title());
        assertEquals(request.getVerified(), response.verified());
    }

    @Test
    void shouldGetEntityById() {
        CreateRequest request = CreateRequest.builder()
                .title("get by id title")
                .description("get by id description")
                .verified(true)
                .build();

        Integer id = entityClient.create(request);
        EntityResponse response = entityClient.getById(id);

        assertEquals(id, response.id());
        assertEquals(request.getTitle(), response.title());
        assertEquals(request.getVerified(), response.verified());
    }

    @Test
    void shouldGetAllEntities() {
        CreateRequest request = CreateRequest.builder()
                .title("get all title")
                .description("get all description")
                .verified(true)
                .build();

        Integer id = entityClient.create(request);
        List<EntityResponse> entities = entityClient.getAll();

        assertNotNull(entities);
        assertFalse(entities.isEmpty());

        boolean entityExists = entities.stream()
                .anyMatch(entity -> entity.id().equals(id));

        assertTrue(entityExists);
    }

    @Disabled("Backend падает на PATCH: nil pointer dereference")
    @Test
    void shouldPatchEntity() {
        CreateRequest request = CreateRequest.builder()
                .title("old title")
                .description("old description")
                .verified(false)
                .build();

        Integer id = entityClient.create(request);

        PatchRequest patchRequest = PatchRequest.builder()
                .title("new title")
                .description("new description")
                .verified(true)
                .build();

        entityClient.patch(id, patchRequest);

        EntityResponse updated = entityClient.getById(id);

        assertEquals(id, updated.id());
        assertEquals(patchRequest.getTitle(), updated.title());
        assertEquals(patchRequest.getVerified(), updated.verified());
    }

    @Test
    void shouldDeleteEntity() {
        CreateRequest request = CreateRequest.builder()
                .title("delete title")
                .description("delete description")
                .verified(true)
                .build();

        Integer id = entityClient.create(request);

        assertNotNull(id);

        entityClient.delete(id);

        List<EntityResponse> entities = entityClient.getAll();

        boolean entityExists = entities.stream()
                .anyMatch(entity -> entity.id().equals(id));

        assertFalse(entityExists);
    }
}