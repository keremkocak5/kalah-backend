package com.kocak.kalah.rule.impl;

import com.kocak.kalah.exception.KalahRuntimeException;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.ErrorCode;
import com.kocak.kalah.rule.Rulable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Rule2UsersTurn implements Rulable {

    private final Rule3TokenCountZero rule3TokenCountZero;

    @Override
    public Optional<Rulable> applyRule(Game game, int pit) {
        if (!game.getTurn().equals(game.getBoards().get(pit).getPlayerSide())) {
            throw new KalahRuntimeException(ErrorCode.NOT_PLAYERS_TURN);
        }

        return Optional.of(getNextRule());
    }

    private Rulable getNextRule() {
        return rule3TokenCountZero;
    }
}
