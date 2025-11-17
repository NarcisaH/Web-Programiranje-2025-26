package unze.ptf.battlearena.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "game_character")
public class GameCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ime je obavezno")
    @Size(min = 2, max = 40, message = "Ime treba imati 2–40 znakova")
    private String name;

    @Min(value = 0) @Max(value = 100)
    private int power;

    @Min(value = 0) @Max(value = 5, message = "Lives 0–5")
    private int lives;

    @Min(0)
    private int points;

    @Min(1)
    private int level; // broj odigranih borbi / trenutni level

    @ManyToMany(fetch = FetchType.LAZY) //ne učitava povezane objekte odmah, nego samo kad se zatraže.
    @JoinTable(
            name = "character_tools",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "tool_id")
    )
    @JsonIgnoreProperties("characters") //sprječava kružno pozivanje u JSON-u. da program ne upada u beskonačnu petlju pozivanja zbog dvosmjerne JPA rezultirane ManyToMany vezom
    private List<Tool> tools;

    public GameCharacter() {}

    public GameCharacter(Long id, String name, int power, int lives, int points) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.lives = lives;
        this.points = points;
        this.level = 0;
    }



    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPower() { return power; }
    public int getLives() { return lives; }
    public int getPoints() { return points; }
    public List<Tool> getTools() { return tools; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPower(int power) { this.power = power; }
    public void setLives(int lives) { this.lives = lives; }
    public void setPoints(int points) { this.points = points; }
    public void setTools(List<Tool> tools) { this.tools = tools; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public int totalPower() {
        int boost = tools.stream().mapToInt(Tool::getPowerBoost).sum();
        return power + boost;
    }

    public int totalLives() {
        int boost = tools.stream().mapToInt(Tool::getLifeBoost).sum();
        int total = lives + boost;
        return Math.min(total, 5); // globalni limit 5
    }

    public boolean canAddTool() {
        return tools.size() < 3;
    }

    public void addTool(Tool t) {
        if (canAddTool()) {
            tools.add(t);
        }
    }
}
