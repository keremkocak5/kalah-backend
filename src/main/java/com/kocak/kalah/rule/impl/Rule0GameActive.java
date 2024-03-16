package com.kocak.kalah.rule.impl;

import com.kocak.kalah.exception.KalahRuntimeException;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.ErrorCode;
import com.kocak.kalah.rule.Ruleable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Rule0GameActive implements Ruleable {

    private final Rule1ValidPit rule1ValidPit;

    @Override
    public Optional<Ruleable> applyRule(Game game, int pit) {
        if (!game.isActive()) {
            throw new KalahRuntimeException(ErrorCode.GAME_NOT_ACTIVE);
        }
        return Optional.of(getNextRule());
    }

    private Ruleable getNextRule() {
        return rule1ValidPit;
    }
}
