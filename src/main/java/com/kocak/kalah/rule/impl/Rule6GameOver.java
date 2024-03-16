package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.Ruleable;
import org.springframework.stereotype.Service;

@Service
public class Rule6GameOver implements Ruleable {

    @Override
    public Ruleable applyRule(Game game, int pit) {
        System.out.println("ishameover");
        return getNextRule();
    }

    private Ruleable getNextRule() {
        return null;
    }
}
