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
        int lastIndex = game.getLastIndex();
        game.getBoards().get(game.getKalahIndexOfCurrentPlayer()).incrementTokenCount(game.getBoards().get(lastIndex).getTokenCount());
        game.getBoards().get(lastIndex).resetTokenCount(); // metodlari ayir

        game.getBoards().get(game.getKalahIndexOfCurrentPlayer()).incrementTokenCount(game.getBoards().get(game.getOppositePit(lastIndex)).getTokenCount());
        game.getBoards().get(game.getOppositePit(lastIndex)).resetTokenCount();

        return Optional.of(getNextRule());
    }

    private Rulable getNextRule() {
        return rule7SwitchSide;
    }
}
