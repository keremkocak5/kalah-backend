package com.kocak.kalah.rule.impl;

import com.kocak.kalah.exception.KalahRuntimeException;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.ErrorCode;
import com.kocak.kalah.rule.Ruleable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Rule3TokenCountZero implements Ruleable {

    private final Rule4Sow sow;

    @Override
    public Ruleable applyRule(Game game, int pit) {
        if (game.getBoards().get(pit).getTokenCount() == 0) {
            throw new KalahRuntimeException(ErrorCode.TOKEN_COUNT_ZERO);
        }
        return getNextRule();
    }

    private Ruleable getNextRule() {
        return sow;
    }
}
