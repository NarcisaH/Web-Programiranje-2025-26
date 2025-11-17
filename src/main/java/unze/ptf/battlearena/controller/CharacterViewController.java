package unze.ptf.battlearena.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import unze.ptf.battlearena.model.GameCharacter;
import unze.ptf.battlearena.service.CharacterService;

@Controller
@RequestMapping("/characters")
public class CharacterViewController {

    private final CharacterService service;

    public CharacterViewController(CharacterService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("characters", service.findAll());
        return "characters"; // lista
    }

    // Create
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("character", new GameCharacter());
        model.addAttribute("formTitle", "Novi karakter");
        return "character_form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("character") GameCharacter c,
                       BindingResult br,
                       Model model) {
        if (br.hasErrors()) {
            model.addAttribute("formTitle", (c.getId() == null) ? "Novi karakter" : "Uredi karakter");
            return "character_form";
        }
        service.save(c);
        return "redirect:/characters";
    }

    // Edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        var c = service.findById(id);
        if (c == null) return "redirect:/characters";
        model.addAttribute("character", c);
        model.addAttribute("formTitle", "Uredi karakter");
        return "character_form";
    }

    // Delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/characters";
    }
}
