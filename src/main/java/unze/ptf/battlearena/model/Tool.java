
package unze.ptf.battlearena.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity

public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int powerBoost; // +snaga
    private int lifeBoost;  // 0 ili 1

    @ManyToMany(mappedBy = "tools", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("tools")
    private List<GameCharacter> characters;

    public Tool() {}

    public Tool(Long id, String name, int powerBoost, int lifeBoost) {
        this.id = id;
        this.name = name;
        this.powerBoost = powerBoost;
        this.lifeBoost = lifeBoost;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPowerBoost() { return powerBoost; }
    public int getLifeBoost() { return lifeBoost; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPowerBoost(int powerBoost) { this.powerBoost = powerBoost; }
    public void setLifeBoost(int lifeBoost) { this.lifeBoost = lifeBoost; }
}

