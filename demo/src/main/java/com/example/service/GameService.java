package com.example.service;

import com.example.model.Game;
import com.example.repository.GameRepository;
import com.example.strategy.DiscountContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final DiscountContext discountContext;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.discountContext = new DiscountContext();
    }

    public List<Game> getAllGames() {

        List<Game> games = gameRepository.findAll();

        for (Game game : games) {
            applyDiscount(game);
        }

        return games;
    }

    public Game getGameById(Long id) {

        Game game = gameRepository.findById(id)
                .orElse(null);

        if (game != null) {
            applyDiscount(game);
        }

        return game;
    }

    public Game saveGame(Game game) {

        if (game.getDiscountType() == null) {
            game.setDiscountType("NONE");
        }

        return gameRepository.save(game);
    }

    public void deleteGame(Long id) {

        gameRepository.deleteById(id);
    }

    private void applyDiscount(Game game) {

        if (game.getPrice() == null) {
            game.setFinalPrice(0.0);
            game.setDiscountName("ราคาปกติ");
            return;
        }

        double finalPrice = discountContext.calculatePrice(
                game.getDiscountType(),
                game.getPrice()
        );

        String discountName = discountContext.getDiscountName(
                game.getDiscountType()
        );

        game.setFinalPrice(finalPrice);
        game.setDiscountName(discountName);
    }
}