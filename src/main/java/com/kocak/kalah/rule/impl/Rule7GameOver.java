package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.GameStatus;
import com.kocak.kalah.model.enums.PlayerSide;
import com.kocak.kalah.rule.Ruleable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Rule7GameOver implements Ruleable {

    @Override
    public Optional<Ruleable> applyRule(Game game, int pit) {
        if (isGameOverForPlayer(game, PlayerSide.BLUE) || isGameOverForPlayer(game, PlayerSide.RED)) {
            int playerBlueScore = game.getBoards().get(game.getKalahIndex(PlayerSide.BLUE)).getTokenCount();
            int playerRedScore = game.getBoards().get(game.getKalahIndex(PlayerSide.RED)).getTokenCount();
            switch (Integer.valueOf(playerBlueScore).compareTo(playerRedScore)) {
                case 0 -> game.setStatus(GameStatus.OVER_DRAW);
                case 1 -> {
                    game.setStatus(GameStatus.OVER);
                    game.setWinner(PlayerSide.BLUE);
                }
                case -1 -> {
                    game.setStatus(GameStatus.OVER);
                    game.setWinner(PlayerSide.RED);
                }
            }
        }
        return Optional.empty();
    }

    private boolean isGameOverForPlayer(Game game, PlayerSide playerSide) {
        return game.getBoards().values()
                .stream()
                .filter(board -> playerSide.equals(board.getPlayerSide()))
                .filter(board -> !board.isKalah())
                .filter(board -> board.getTokenCount() > 0)
                .findAny()
                .isEmpty();
    }

}
