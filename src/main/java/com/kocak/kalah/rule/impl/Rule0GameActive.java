package com.kocak.kalah.rule.impl;

import com.kocak.kalah.exception.KalahRuntimeException;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.ErrorCode;
import com.kocak.kalah.rule.Ruleable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Rule0GameActive implements Ruleable {

    private final Rule2PitCountZero pitCountZero;

    @Override
    public Ruleable applyRule(Game game, int pit) {
        if (!game.isActive()) {
            throw new KalahRuntimeException(ErrorCode.GAME_NOT_ACTIVE);
        }
        return getNextRule();
    }

    private Ruleable getNextRule() {
        return pitCountZero;
    }
}
