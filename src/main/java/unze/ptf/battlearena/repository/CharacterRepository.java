package unze.ptf.battlearena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unze.ptf.battlearena.model.GameCharacter;

public interface CharacterRepository extends JpaRepository<GameCharacter, Long> {
}
