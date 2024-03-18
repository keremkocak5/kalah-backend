package com.kocak.kalah.rule.impl;

import com.kocak.kalah.exception.KalahRuntimeException;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.ErrorCode;
import com.kocak.kalah.model.enums.RuleType;
import com.kocak.kalah.rule.Rulable;
import org.springframework.stereotype.Service;

@Service
public class Rule2UsersTurn implements Rulable {

    @Override
    public RuleType applyRule(Game game, int pit) {
        if (!game.getTurn().equals(game.getBoards().get(pit).getPlayerSide())) {
            throw new KalahRuntimeException(ErrorCode.NOT_PLAYERS_TURN);
        }
        return RuleType.REGULAR;
    }

}
