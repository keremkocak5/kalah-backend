package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.Ruleable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Rule5SwitchTurn implements Ruleable {

    private final Rule6GameOver gameOver;

    @Override
    public Ruleable applyRule(Game game, int pit) {
        game.switchSide();
        return getNextRule();
    }

    private Ruleable getNextRule() {
        return gameOver;
    }
}