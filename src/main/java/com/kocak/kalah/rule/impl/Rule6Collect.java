package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.Ruleable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Rule6Collect implements Ruleable {

    private final Rule7SwitchSide rule7SwitchSide;

    @Override
    public Optional<Ruleable> applyRule(Game game, int pit) {
        int lastIndex = game.getLastIndex();
        game.getBoards().get(game.getKalahIndexOfCurrentPlayer()).incrementTokenCount(game.getBoards().get(lastIndex).getTokenCount());
        game.getBoards().get(lastIndex).resetTokenCount();

        game.getBoards().get(game.getKalahIndexOfCurrentPlayer()).incrementTokenCount(game.getBoards().get(game.getOppositePit(lastIndex)).getTokenCount());
        game.getBoards().get(game.getOppositePit(lastIndex)).resetTokenCount();

        return Optional.of(getNextRule());
    }

    private Ruleable getNextRule() {
        return rule7SwitchSide;
    }
}
