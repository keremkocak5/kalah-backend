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
public class Rule0GameActive implements Rulable {

    private final Rule1ValidPit rule1ValidPit;

    @Override
    public Optional<Rulable> applyRule(Game game, int pit) {
        if (!game.isActive()) {
            throw new KalahRuntimeException(ErrorCode.GAME_NOT_ACTIVE);
        }
        return Optional.of(getNextRule());
    }

    private Rulable getNextRule() {
        return rule1ValidPit;
    }
}
