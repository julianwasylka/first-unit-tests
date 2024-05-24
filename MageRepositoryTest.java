package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MageRepositoryTest {

    private MageRepository repository;
    private Mage testMage;

    @BeforeEach
    void setUp() {
        repository = new MageRepository();
        testMage = new Mage("Merlin", 10);
        repository.save(testMage);
    }

    @Test
    void testFindExistingMage() {
        Optional<Mage> result = repository.find("Merlin");
        assertEquals(testMage, result.get());
    }

    @Test
    void testFindNonExistingMage() {
        Optional<Mage> result = repository.find("Gandalf");
        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteExistingMage() {
        repository.delete("Merlin");
        Optional<Mage> result = repository.find("Merlin");
        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteNonExistingMage() {
        assertThrows(IllegalArgumentException.class, () -> repository.delete("Gandalf"));
    }

    @Test
    void testSaveNewMage() {
        Mage newMage = new Mage("Gandalf", 20);
        repository.save(newMage);
        Optional<Mage> result = repository.find("Gandalf");
        assertEquals(newMage, result.get());
    }

    @Test
    void testSaveExistingMage() {
        Mage existingMage = new Mage("Merlin", 15);
        assertThrows(IllegalArgumentException.class, () -> repository.save(existingMage));
    }
}
