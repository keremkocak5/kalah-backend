package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.Ruleable;
import org.springframework.stereotype.Service;

@Service
public class Rule7GameOver implements Ruleable {

    @Override
    public Ruleable applyRule(Game game, int pit) {
        return getNextRule();
    }

    private Ruleable getNextRule() {
        return null;
    }
}
