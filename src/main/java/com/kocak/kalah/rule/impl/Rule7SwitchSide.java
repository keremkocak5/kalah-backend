package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.Rulable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Rule7SwitchSide implements Rulable {

    private final Rule8GameOver gameOver;

    @Override
    public Optional<Rulable> applyRule(Game game, int pit) {
        game.switchSide();

        return Optional.of(getNextRule());
    }

    private Rulable getNextRule() {
        return gameOver;
    }
}