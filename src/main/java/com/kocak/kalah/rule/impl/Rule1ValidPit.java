package com.kocak.kalah.rule.impl;

import com.kocak.kalah.exception.KalahRuntimeException;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.ErrorCode;
import com.kocak.kalah.model.enums.RuleType;
import com.kocak.kalah.rule.Rulable;
import org.springframework.stereotype.Service;

@Service
public class Rule1ValidPit implements Rulable {

    @Override
    public RuleType applyRule(Game game, int pit) {
        if (!game.getBoards().containsKey(pit)) {
            throw new KalahRuntimeException(ErrorCode.NO_SUCH_PIT_FOUND);
        }
        return RuleType.REGULAR;
    }

}
