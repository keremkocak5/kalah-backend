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
public class Rule3TokenCountZero implements Rulable {

    private final Rule4PitKalah rule4PitKalah;

    @Override
    public Optional<Rulable> applyRule(Game game, int pit) {
        if (game.getBoards().get(pit).getTokenCount() == 0) {
            throw new KalahRuntimeException(ErrorCode.TOKEN_COUNT_ZERO);
        }
        return Optional.of(getNextRule());
    }

    private Rulable getNextRule() {
        return rule4PitKalah;
    }
}
