package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.Ruleable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Rule6SwitchSide implements Ruleable {

    private final Rule7GameOver gameOver;

    @Override
    public Optional<Ruleable> applyRule(Game game, int pit) {
        game.switchSide();
        return Optional.of(getNextRule());
    }

    private Ruleable getNextRule() {
        return gameOver;
    }
}