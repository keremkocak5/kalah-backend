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
public class Rule4PitKalah implements Rulable {

    private final Rule5Sow rule5Sow;

    @Override
    public Optional<Rulable> applyRule(Game game, int pit) {
        if (game.getBoards().get(pit).isKalah()) {
            throw new KalahRuntimeException(ErrorCode.CANNOT_PLAY_KALAH);
        }

        return Optional.of(getNextRule());
    }

    private Rulable getNextRule() {
        return rule5Sow;
    }
}
