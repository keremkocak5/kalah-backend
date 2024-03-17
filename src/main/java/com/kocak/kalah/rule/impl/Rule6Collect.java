package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.Rulable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Rule6Collect implements Rulable {

    private final Rule7SwitchSide rule7SwitchSide;

    @Override
    public Optional<Rulable> applyRule(Game game, int pit) {
        collectTokensToKalah(game, game.getLastIndex());
        collectTokensToKalah(game, game.getOppositePit(game.getLastIndex()));

        return Optional.of(getNextRule());
    }

    private void collectTokensToKalah(Game game, int index) {
        game.getBoards().get(game.getKalahIndexOfCurrentPlayer()).incrementTokenCount(game.getBoards().get(index).getTokenCount());
        game.getBoards().get(index).resetTokenCount();
    }

    private Rulable getNextRule() {
        return rule7SwitchSide;
    }
}
