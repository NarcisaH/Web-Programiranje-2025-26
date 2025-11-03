package unze.ptf.battlearena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unze.ptf.battlearena.model.GameCharacter;
import unze.ptf.battlearena.service.CharacterService;

@Controller
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService service;

    public CharacterController(CharacterService service) {
        this.service = service;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("characters", service.findAll());
        return "characters";
    }

    @PostMapping("/add")
    public String add(GameCharacter c) {
        service.save(c);
        return "redirect:/characters";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/characters";
    }
}









/*
@Controller
public class CharacterController {

    private final GameData data;
    private final BattleService battleService;

    public CharacterController(GameData data, BattleService battleService) {
        this.data = data;
        this.battleService = battleService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/characters";
    }

    @GetMapping("/characters")
    public String characters(Model model) {
        model.addAttribute("characters", data.findAllCharacters());
        return "characters";
    }

    @GetMapping("/tools")
    public String tools(Model model) {
        model.addAttribute("tools", data.findAllTools());
        model.addAttribute("characters", data.findAllCharacters());
        return "tools";
    }

    // “Kupovina” alata za demo: cijena 3 boda (možeš lako promijeniti)
    @PostMapping("/tools/buy")
    public String buyTool(@RequestParam Long characterId, @RequestParam Long toolId) {
        data.buyTool(characterId, toolId, 3);
        return "redirect:/tools";
    }

    @GetMapping("/battle")
    public String battle(Model model,
                         @RequestParam(required = false) Long characterId,
                         @RequestParam(required = false, defaultValue = "1") int level) {
        model.addAttribute("characters", data.findAllCharacters());
        model.addAttribute("selectedId", characterId);
        model.addAttribute("level", level);
        return "battle";
    }

    @PostMapping("/battle/start")
    public String startBattle(@RequestParam Long characterId,
                              @RequestParam int level,
                              Model model) {

        GameCharacter c = data.findCharacter(characterId);
        // Koristi c.getLevel(), a ne "level" iz forme
        var result = battleService.simulate(c);

        model.addAttribute("characters", data.findAllCharacters());
        model.addAttribute("selectedId", characterId);
        model.addAttribute("character", c);
        model.addAttribute("result", result);
        return "battle";
    }
}*/
