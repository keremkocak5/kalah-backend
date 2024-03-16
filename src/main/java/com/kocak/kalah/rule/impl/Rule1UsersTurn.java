package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.Ruleable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Rule1UsersTurn implements Ruleable {

    private final Rule2PitCountZero pitCountZero;

    @Override
    public Ruleable applyRule(Game game, int pit) {
        //if (game.getTurn().equals(game.getBoards().stream().map())
        return getNextRule();
    }

    private Ruleable getNextRule() {
        return pitCountZero;
    }
}
