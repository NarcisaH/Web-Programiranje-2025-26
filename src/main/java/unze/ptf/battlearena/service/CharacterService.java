package unze.ptf.battlearena.service;

import org.springframework.stereotype.Service;
import unze.ptf.battlearena.model.GameCharacter;
import unze.ptf.battlearena.model.GameCharacter;
import unze.ptf.battlearena.repository.CharacterRepository;
import java.util.List;

@Service
public class CharacterService {
    private final CharacterRepository repo;

    public CharacterService(CharacterRepository repo) {
        this.repo = repo;
    }

    public List<GameCharacter> findAll() {
        return repo.findAll();
    }

    public GameCharacter findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public GameCharacter save(GameCharacter c) {
        return repo.save(c);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
