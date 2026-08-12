package com.example.controller;

import com.example.model.Game;
import com.example.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // =========================
    // READ
    // =========================

    @GetMapping("/games")
    public String listGames(Model model) {

        model.addAttribute(
                "games",
                gameService.getAllGames()
        );

        return "games/list";
    }

    // =========================
    // CREATE - Show Form
    // =========================

    @GetMapping("/games/add")
    public String showAddForm(Model model) {

        Game game = new Game();

        game.setDiscountType("NONE");

        model.addAttribute("game", game);

        return "games/add";
    }

    // =========================
    // CREATE - Save
    // =========================

    @PostMapping("/games/save")
    public String saveGame(
            @ModelAttribute("game") Game game,
            RedirectAttributes redirectAttributes) {

        gameService.saveGame(game);

        redirectAttributes.addFlashAttribute(
                "message",
                "เพิ่มเกมสำเร็จ"
        );

        return "redirect:/games";
    }

    // =========================
    // UPDATE - Show Form
    // =========================

    @GetMapping("/games/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Game game = gameService.getGameById(id);

        if (game == null) {
            return "redirect:/games";
        }

        model.addAttribute("game", game);

        return "games/edit";
    }

    // =========================
    // UPDATE - Save
    // =========================

    @PostMapping("/games/update/{id}")
    public String updateGame(
            @PathVariable Long id,
            @ModelAttribute("game") Game game,
            RedirectAttributes redirectAttributes) {

        game.setId(id);

        gameService.saveGame(game);

        redirectAttributes.addFlashAttribute(
                "message",
                "แก้ไขข้อมูลเกมสำเร็จ"
        );

        return "redirect:/games";
    }

    // =========================
    // DELETE - Confirmation
    // =========================

    @GetMapping("/games/delete/{id}")
    public String showDeleteForm(
            @PathVariable Long id,
            Model model) {

        Game game = gameService.getGameById(id);

        if (game == null) {
            return "redirect:/games";
        }

        model.addAttribute("game", game);

        return "games/delete";
    }

    // =========================
    // DELETE
    // =========================

    @PostMapping("/games/delete/{id}")
    public String deleteGame(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        gameService.deleteGame(id);

        redirectAttributes.addFlashAttribute(
                "message",
                "ลบเกมสำเร็จ"
        );

        return "redirect:/games";
    }
}