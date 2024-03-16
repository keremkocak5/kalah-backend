package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.Ruleable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Rule2PitCountZero implements Ruleable {

    private final Rule3Sow sow;

    @Override
    public Ruleable applyRule(Game game, int pit) {
        System.out.println("r2");
        return getNextRule();
    }

    private Ruleable getNextRule() {
        return sow;
    }
}
