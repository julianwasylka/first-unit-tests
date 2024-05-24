package org.example;
import java.util.Map;
import java.util.Optional;

public class MageController {
    private MageRepository repository;

    public MageController(MageRepository repository) {
        this.repository = repository;
    }

    public String find(String name) {
        Optional<Mage> mageOptional = repository.find(name);
        return mageOptional.map(this::convertMageToString).orElse("not found");
    }

    public String delete(String name) {
        try {
            repository.delete(name);
            return "done";
        } catch (IllegalArgumentException e) {
            return "not found";
        }
    }

    public String save(String name, int level) {
        Mage mage = new Mage(name, level);
        try {
            repository.save(mage);
            return "done";
        } catch (IllegalArgumentException e) {
            return "bad request";
        }
    }

    private String convertMageToString(Mage mage) {
        return "Name: " + mage.getName() + ", Level: " + mage.getLevel();
    }
}
