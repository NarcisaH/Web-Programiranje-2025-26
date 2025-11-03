package unze.ptf.battlearena.controller;

import org.springframework.web.bind.annotation.*;
import unze.ptf.battlearena.model.GameCharacter;
import unze.ptf.battlearena.service.CharacterService;
import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterRestController {

    private final CharacterService service;

    public CharacterRestController(CharacterService service) {
        this.service = service;
    }

    @GetMapping
    public List<GameCharacter> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public GameCharacter getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public GameCharacter add(@RequestBody GameCharacter c) {
        return service.save(c);
    }

    @PutMapping("/{id}")
    public GameCharacter update(@PathVariable Long id, @RequestBody GameCharacter c) {
        c.setId(id);
        return service.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
