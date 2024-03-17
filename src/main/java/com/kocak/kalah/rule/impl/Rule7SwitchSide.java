package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.Ruleable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Rule7SwitchSide implements Ruleable {

    private final Rule8GameOver gameOver;

    @Override
    public Optional<Ruleable> applyRule(Game game, int pit) {
        game.switchSide();
        return Optional.of(getNextRule());
    }

    private Ruleable getNextRule() {
        return gameOver;
    }
}