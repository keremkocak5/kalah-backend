package com.kocak.kalah.rule2;

import com.kocak.kalah.model.entity.Board;
import com.kocak.kalah.model.entity.Game;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Sow implements Ruleable {
    public boolean isRuleMeetsCondition(Game game, int pit) {

        System.out.println("meets  sow");
        return true;
    }

    public Game applyRule(Game game, int pit) {
        System.out.println("apply sow");
        Map<Integer, Board> boards = game.getBoards().stream().collect(Collectors.toMap(p -> p.getPit(), p -> p));
        int tokenCountBeforeReset = boards.get(pit).getTokenCount();
        boards.get(pit).resetTokenCount();
        int i = pit+1;
        while(tokenCountBeforeReset >0) {
            if (boards.get(i % game.getEffectivePitCount()).isKalah() && !boards.get(i % game.getEffectivePitCount()).getPlayerSide().equals(game.getTurn())) { // kerem buralar elden gecsin
                i++;
            }
            boards.get(i % game.getEffectivePitCount()).incrementTokenCount();
            tokenCountBeforeReset  --;
            i++;
        }
        return game;

    }

    @Override
    public int getRuleOrder() {
        return 2;
    }

    @Override
    public int getRuleOrder2() {
        return 5;
    }
}
