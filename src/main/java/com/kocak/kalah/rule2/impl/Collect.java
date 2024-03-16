package com.kocak.kalah.rule2.impl;

import com.kocak.kalah.model.entity.Board;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule2.Ruleable2;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Collect implements Ruleable2 {

    @Override
    public boolean isRuleApplicable(Game game, int pit) {
        Map<Integer, Board> boards = game.getBoards().stream().collect(Collectors.toMap(p -> p.getPit(), p -> p));
        int tokenCountBeforeReset = boards.get(pit).getTokenCount();
        int i = pit + 1;
        boolean last = false;
        while (tokenCountBeforeReset > 0) {
            if (boards.get(i % game.getEffectivePitCount()).isKalah() && !boards.get(i % game.getEffectivePitCount()).getPlayerSide().equals(game.getTurn())) { // kerem buralar elden gecsin
                i++;
            }
            last = boards.get(i % game.getEffectivePitCount()).getTokenCount()==0 && boards.get(i % game.getEffectivePitCount()).getPlayerSide().equals(game.getTurn()) && !boards.get(i % game.getEffectivePitCount()).isKalah();
            tokenCountBeforeReset--;
            i++;
        }
        return last;
    }

    @Override
    public Game applyRule(Game game, int pit) {
        Map<Integer, Board> boards = game.getBoards().stream().collect(Collectors.toMap(p -> p.getPit(), p -> p));
        int i = pit + 1;
        int m = boards.get(i % game.getEffectivePitCount()).getTokenCount(); // swap isi game icine girmeli kerem
        boards.get(i % game.getEffectivePitCount()).resetTokenCount();
        boards.entrySet().stream().filter(integerBoardEntry -> integerBoardEntry.getValue().isKalah() && integerBoardEntry.getValue().getPlayerSide().equals(game.getTurn()))
                .forEach(integerBoardEntry -> integerBoardEntry.getValue().incrementTokenCount(m));
        // opponent sifirla ve benim kaleye at
        return game;
    }

    @Override
    public int getRuleOrder() {
        return 3;
    }

    @Override
    public int getRuleOrder2() {
        return 8;
    }
}
