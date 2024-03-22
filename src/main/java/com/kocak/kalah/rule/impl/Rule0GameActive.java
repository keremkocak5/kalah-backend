package com.kocak.kalah.rule.impl;

import com.kocak.kalah.exception.KalahRuntimeException;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.KalahError;
import com.kocak.kalah.model.enums.RuleType;
import com.kocak.kalah.rule.Rulable;
import org.springframework.stereotype.Service;

@Service
public class Rule0GameActive implements Rulable {

    @Override
    public RuleType applyRule(Game game, int pit) {
        if (!game.isActive()) {
            throw new KalahRuntimeException(KalahError.GAME_NOT_ACTIVE);
        }
        return RuleType.REGULAR;
    }

}
