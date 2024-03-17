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
public class Rule1ValidPit implements Rulable {

    private final Rule2UsersTurn rule2UsersTurn;

    @Override
    public Optional<Rulable> applyRule(Game game, int pit) {
        if (!game.getBoards().containsKey(pit)) {
            throw new KalahRuntimeException(ErrorCode.NO_SUCH_PIT_FOUND);
        }

        return Optional.of(getNextRule());
    }

    private Rulable getNextRule() {
        return rule2UsersTurn;
    }
}
