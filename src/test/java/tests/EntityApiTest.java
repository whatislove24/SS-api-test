package tests;

import dto.Addition;
import dto.CreateRequest;
import dto.EntityResponse;
import dto.PatchRequest;
import org.junit.jupiter.api.Test;
import tests.base.BaseApiTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityApiTest extends BaseApiTest {

    @Test
     void shouldCreateEntity() {
        CreateRequest request = new CreateRequest(
                "create title",
                "create description",
                true
        );

        Integer id = entityClient.create(request);
        EntityResponse response = entityClient.getById(id);

        assertNotNull(response, "Response should not be null");
        assertEquals(id, response.id(), "ID should match");
        assertEquals(request.title(), response.title(), "Title should match");
        assertEquals(request.verified(), response.verified(), "Verified should match");
    }

    @Test
   void shouldGetEntityById() {
        CreateRequest request = new CreateRequest(
                "get by id title",
                "get by id description",
                true
        );

        Integer id = entityClient.create(request);
        EntityResponse response = entityClient.getById(id);

        assertEquals(id, response.id(), "ID should match");
        assertEquals(request.title(), response.title(), "Title should match");
        assertEquals(request.verified(), response.verified(), "Verified should match");
    }

    @Test
   void shouldGetAllEntities() {
        CreateRequest request = new CreateRequest(
                "get all title",
                "get all description",
                true
        );

        Integer id = entityClient.create(request);
        List<EntityResponse> entities = entityClient.getAll();

        assertNotNull(entities, "Entities list should not be null");
        assertFalse(entities.isEmpty(), "Entities list should not be empty");

        boolean exists = entities.stream()
                .anyMatch(e -> e.id().equals(id));

        assertTrue(exists, "Created entity should exist in list");
    }

    @Test
    void shouldPatchEntity() {
        CreateRequest create = new CreateRequest(
                "old title",
                "old description",
                false
        );

        Integer id = entityClient.create(create);

        PatchRequest patch = new PatchRequest(
                new Addition("Дополнительные сведения", 123),
                List.of(42, 87, 15),
                "new title",
                true
        );

        entityClient.patch(id, patch);

        EntityResponse updated = entityClient.getById(id);

        assertEquals("new title", updated.title(), "Title should be updated");
        assertTrue(updated.verified(), "Verified should be true");
    }

    @Test
    void shouldDeleteEntity() {
        CreateRequest request = new CreateRequest(
                "delete title",
                "delete description",
                true
        );

        Integer id = entityClient.create(request);

        entityClient.delete(id);

        List<EntityResponse> entities = entityClient.getAll();

        boolean exists = entities.stream()
                .anyMatch(e -> e.id().equals(id));

        assertFalse(exists, "Entity should be deleted");
    }
}