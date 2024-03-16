package com.kocak.kalah.rule.impl;

import com.kocak.kalah.exception.KalahRuntimeException;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.ErrorCode;
import com.kocak.kalah.rule.Ruleable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Rule1ValidPit implements Ruleable {

    private final Rule3TokenCountZero pitCountZero;

    @Override
    public Ruleable applyRule(Game game, int pit) {
        if (game.getBoards().containsKey(pit)) {
            throw new KalahRuntimeException(ErrorCode.NO_SUCH_PIT_FOUND);
        }
        return getNextRule();
    }

    private Ruleable getNextRule() {
        return pitCountZero;
    }
}
