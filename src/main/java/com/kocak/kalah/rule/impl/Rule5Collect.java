package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.Ruleable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Rule5Collect implements Ruleable {

    private final Rule6SwitchSide rule6SwitchSide;

    @Override
    public Optional<Ruleable> applyRule(Game game, int pit) {
        int i = pit + 1;
        int m = game.getBoards().get(i % game.getEffectivePitCount()).getTokenCount(); // swap isi game icine girmeli kerem
        game.getBoards().get(i % game.getEffectivePitCount()).resetTokenCount();
        game.getBoards().entrySet().stream().filter(integerBoardEntry -> integerBoardEntry.getValue().isKalah() && integerBoardEntry.getValue().getPlayerSide().equals(game.getTurn()))
                .forEach(integerBoardEntry -> integerBoardEntry.getValue().incrementTokenCount(m + game.getBoards().get(game.getOppositePit(pit + 1)).getTokenCount()));
        game.getBoards().get(game.getOppositePit(pit + 1)).resetTokenCount();
        return Optional.of(getNextRule());
    }

    private Ruleable getNextRule() {
        return rule6SwitchSide;
    }
}
