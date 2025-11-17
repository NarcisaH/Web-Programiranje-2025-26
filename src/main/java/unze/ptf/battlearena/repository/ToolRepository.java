package unze.ptf.battlearena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unze.ptf.battlearena.model.Tool;

public interface ToolRepository extends JpaRepository<Tool, Long> {
}
