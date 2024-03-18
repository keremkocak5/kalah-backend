package com.kocak.kalah.rule.impl;

import com.kocak.kalah.exception.KalahRuntimeException;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.ErrorCode;
import com.kocak.kalah.model.enums.RuleType;
import com.kocak.kalah.rule.Rulable;
import org.springframework.stereotype.Service;

@Service
public class Rule3TokenCountZero implements Rulable {

    @Override
    public RuleType applyRule(Game game, int pit) {
        if (game.getBoards().get(pit).getTokenCount() == 0) {
            throw new KalahRuntimeException(ErrorCode.TOKEN_COUNT_ZERO);
        }
        return RuleType.REGULAR;
    }

}
