package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MageControllerTest {

    private MageRepository repository;
    private MageController controller;

    @BeforeEach
    void setUp() {
        repository = mock(MageRepository.class);
        controller = new MageController(repository);
    }

    @Test
    void testFindExistingMage() {
        Mage testMage = new Mage("Merlin", 10);
        when(repository.find("Merlin")).thenReturn(Optional.of(testMage));
        String result = controller.find("Merlin");
        assertEquals("Name: Merlin, Level: 10", result);
    }

    @Test
    void testFindNonExistingMage() {
        when(repository.find("Gandalf")).thenReturn(Optional.empty());
        String result = controller.find("Gandalf");
        assertEquals("not found", result);
    }

    @Test
    void testDeleteExistingMage() {
        String result = controller.delete("Merlin");
        assertEquals("done", result);
        verify(repository).delete("Merlin");
    }

    @Test
    void testDeleteNonExistingMage() {
        doThrow(IllegalArgumentException.class).when(repository).delete(any(String.class));
        String result = controller.delete("Yen");
        assertEquals("not found", result);
    }

    @Test
    void testSaveNewMage() {
        String result = controller.save("Gandalf", 20);
        assertEquals("done", result);
        verify(repository).save(any(Mage.class));
    }

    @Test
    void testSaveExistingMage() {
        doThrow(IllegalArgumentException.class).when(repository).save(any(Mage.class));
        String result = controller.save("Merlin", 15);
        assertEquals("bad request", result);
    }
}