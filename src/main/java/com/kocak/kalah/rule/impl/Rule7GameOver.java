package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Board;
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
        Optional<Board> playerRedNonEmptyPit = isGameOverForPlayer(game, PlayerSide.RED);
        Optional<Board> playerBlueNonEmptyPit = isGameOverForPlayer(game, PlayerSide.BLUE);

        if (playerRedNonEmptyPit.isEmpty() && playerBlueNonEmptyPit.isEmpty()) {
            game.setStatus(GameStatus.OVER_DRAW);
        } else if (playerRedNonEmptyPit.isEmpty()) {
            game.setStatus(GameStatus.OVER_DRAW);
            game.setWinner(PlayerSide.RED);
        } else if (playerBlueNonEmptyPit.isEmpty()) {
            game.setStatus(GameStatus.OVER_DRAW);
            game.setWinner(PlayerSide.BLUE);
        }

        return Optional.empty();
    }

    private Optional<Board> isGameOverForPlayer(Game game, PlayerSide playerSide) {
        return game.getBoards().values().stream()
                .filter(board -> playerSide.equals(board.getPlayerSide()))
                .filter(board -> !board.isKalah())
                .filter(board -> board.getTokenCount() > 0).findAny();
    }

}
