package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.Ruleable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Rule4Sow implements Ruleable {

    private final Rule7GameOver rule7GameOver; // kerem check names
    private final Rule6SwitchTurn rule6SwitchTurn;
    private final Rule5Collect rule5Collect;

    @Override
    public Optional<Ruleable> applyRule(Game game, int pit) {
        int tokenCountBeforeReset = game.getBoards().get(pit).getTokenCount();
        game.getBoards().get(pit).resetTokenCount();
        int i = pit + 1;
        boolean lastIsKalah = false;
        boolean lastIsKalah2 = false;
        while (tokenCountBeforeReset > 0) {
            if (game.getBoards().get(i % game.getEffectivePitCount()).isKalah() && !game.getBoards().get(i % game.getEffectivePitCount()).getPlayerSide().equals(game.getTurn())) { // kerem buralar elden gecsin
                i++;
            }
            lastIsKalah = game.getBoards().get(i % game.getEffectivePitCount()).isKalah();
            lastIsKalah2 = !game.getBoards().get(i % game.getEffectivePitCount()).isKalah() && game.getBoards().get(i % game.getEffectivePitCount()).getTokenCount() == 0 && game.getBoards().get(i % game.getEffectivePitCount()).getPlayerSide().equals(game.getTurn());
            game.getBoards().get(i % game.getEffectivePitCount()).incrementTokenCount();
            tokenCountBeforeReset--;

            i++;

        }
        return Optional.of(getNextRule(lastIsKalah, lastIsKalah2));
    }

    private Ruleable getNextRule(boolean lastIsKalah, boolean lastIsZero) {
        if (lastIsKalah)
            return rule7GameOver;
        if (lastIsZero)
            return rule5Collect;
        return rule6SwitchTurn;
    }
}
