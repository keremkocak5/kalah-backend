package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.GameStatus;
import com.kocak.kalah.model.enums.PlayerSide;
import com.kocak.kalah.model.enums.RuleType;
import com.kocak.kalah.rule.Rulable;
import org.springframework.stereotype.Service;

@Service
public class Rule8GameOver implements Rulable {

    @Override
    public RuleType applyRule(Game game, int pit) { // kerem burada hala is var!
        if (isAllNonKalahPitsOfPlayerEmpty(game, PlayerSide.BLUE) || isAllNonKalahPitsOfPlayerEmpty(game, PlayerSide.RED)) {
            int playerBlueTokenCountInKalah = game.getBoards().get(game.getKalahIndex(PlayerSide.BLUE)).getTokenCount();
            int playerRedTokenCountInKalah = game.getBoards().get(game.getKalahIndex(PlayerSide.RED)).getTokenCount();
            switch (Integer.valueOf(playerBlueTokenCountInKalah).compareTo(playerRedTokenCountInKalah)) {
                case 0 -> game.setStatus(GameStatus.GAME_OVER_DRAW);
                case 1 -> {
                    game.setStatus(GameStatus.GAME_OVER);
                    game.setWinner(PlayerSide.BLUE);
                }
                case -1 -> {
                    game.setStatus(GameStatus.GAME_OVER);
                    game.setWinner(PlayerSide.RED);
                }
            }
        }
        return RuleType.REGULAR;
    }

    private boolean isAllNonKalahPitsOfPlayerEmpty(Game game, PlayerSide playerSide) {
        return game.getBoards().values()
                .stream()
                .filter(board -> playerSide.equals(board.getPlayerSide()))
                .filter(board -> !board.isKalah())
                .filter(board -> board.getTokenCount() > 0)
                .findAny()
                .isEmpty();
    }

}
