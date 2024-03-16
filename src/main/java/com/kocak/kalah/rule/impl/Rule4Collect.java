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
public class Rule4Collect implements Ruleable {

    private final Rule5SwitchTurn switchTurn;

    @Override
    public Ruleable applyRule(Game game, int pit) {
        Map<Integer, Board> boards = game.getBoards().stream().collect(Collectors.toMap(p -> p.getPit(), p -> p));
        int i = pit + 1;
        int m = boards.get(i % game.getEffectivePitCount()).getTokenCount(); // swap isi game icine girmeli kerem
        boards.get(i % game.getEffectivePitCount()).resetTokenCount();
        boards.entrySet().stream().filter(integerBoardEntry -> integerBoardEntry.getValue().isKalah() && integerBoardEntry.getValue().getPlayerSide().equals(game.getTurn()))
                .forEach(integerBoardEntry -> integerBoardEntry.getValue().incrementTokenCount(m));
        return getNextRule();
    }

    private Ruleable getNextRule() {
        return switchTurn;
    }
}
