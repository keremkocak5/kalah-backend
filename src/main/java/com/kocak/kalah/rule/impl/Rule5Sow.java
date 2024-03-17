package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.Rulable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Rule5Sow implements Rulable {

    private final Rule8GameOver rule8GameOver;
    private final Rule7SwitchSide rule7SwitchSide;
    private final Rule6Collect rule6Collect;

    @Override
    public Optional<Rulable> applyRule(Game game, int pit) { // kerem dikkat kontrol
        int tokenCountBeforeReset = game.getBoards().get(pit).getTokenCount();
        game.getBoards().get(pit).resetTokenCount();
        boolean lastIsKalah = false;
        boolean lastIsKalah2 = false;
        while (tokenCountBeforeReset > 0) {
            pit++;
            if (game.getBoards().get(pit % game.getEffectivePitCount()).isKalah() && !game.getBoards().get(pit % game.getEffectivePitCount()).getPlayerSide().equals(game.getTurn())) { // kerem buralar elden gecsin
                pit++;
            }
            lastIsKalah = game.getBoards().get(pit % game.getEffectivePitCount()).isKalah();
            lastIsKalah2 = !game.getBoards().get(pit % game.getEffectivePitCount()).isKalah() && game.getBoards().get(pit % game.getEffectivePitCount()).getTokenCount() == 0 && game.getBoards().get(pit % game.getEffectivePitCount()).getPlayerSide().equals(game.getTurn());
            game.getBoards().get(pit % game.getEffectivePitCount()).incrementTokenCount();
            tokenCountBeforeReset--;
        }
        game.setLastIndex(pit - 1 % game.getEffectivePitCount());

        return Optional.of(getNextRule(lastIsKalah, lastIsKalah2));
    }

    private Rulable getNextRule(boolean lastIsKalah, boolean lastIsZero) {
        if (lastIsKalah) {
            return rule8GameOver;
        } else if (lastIsZero) {
            return rule6Collect;
        }
        return rule7SwitchSide;
    }
}
