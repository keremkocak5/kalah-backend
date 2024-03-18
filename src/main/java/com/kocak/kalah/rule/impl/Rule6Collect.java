package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.RuleType;
import com.kocak.kalah.rule.Rulable;
import org.springframework.stereotype.Service;

@Service
public class Rule6Collect implements Rulable {

    @Override
    public RuleType applyRule(Game game, int pit) {
        collectTokensToKalah(game, game.getLastIndex()); // kerem bunda defect var
        collectTokensToKalah(game, game.getOppositePit(game.getLastIndex()));
        return RuleType.REGULAR;
    }

    private void collectTokensToKalah(Game game, int index) {
        game.getBoards().get(game.getKalahIndexOfCurrentPlayer()).incrementTokenCount(game.getBoards().get(index).getTokenCount());
        game.getBoards().get(index).resetTokenCount();
    }

}
