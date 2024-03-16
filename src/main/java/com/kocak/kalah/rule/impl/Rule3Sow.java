package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Board;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.Ruleable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Rule3Sow implements Ruleable {

    private final Rule6GameOver gameOver; // kerem check names
    private final Rule5SwitchTurn switchTurn;
    private final Rule4Collect rule4Collect;

    @Override
    public Ruleable applyRule(Game game, int pit) {
        System.out.println("apply sow");
        Map<Integer, Board> boards = game.getBoards().stream().collect(Collectors.toMap(p -> p.getPit(), p -> p));
        int tokenCountBeforeReset = boards.get(pit).getTokenCount();
        boards.get(pit).resetTokenCount();
        int i = pit + 1;
        boolean lastIsKalah = false;
        boolean lastIsKalah2 = false;
        while (tokenCountBeforeReset > 0) {
            if (boards.get(i % game.getEffectivePitCount()).isKalah() && !boards.get(i % game.getEffectivePitCount()).getPlayerSide().equals(game.getTurn())) { // kerem buralar elden gecsin
                i++;
            }
                lastIsKalah = boards.get(i % game.getEffectivePitCount()).isKalah();
                lastIsKalah2 = !boards.get(i % game.getEffectivePitCount()).isKalah() && boards.get(i % game.getEffectivePitCount()).getTokenCount() == 0 && boards.get(i % game.getEffectivePitCount()).getPlayerSide().equals(game.getTurn());
            boards.get(i % game.getEffectivePitCount()).incrementTokenCount();
            tokenCountBeforeReset--;

            i++;

        }
        return getNextRule(lastIsKalah, lastIsKalah2);
    }

    private Ruleable getNextRule(boolean lastIsKalah, boolean lastIsZero) {
        if (lastIsKalah)
            return gameOver;
        if (lastIsZero)
            return rule4Collect;
        return switchTurn;
    }
}
