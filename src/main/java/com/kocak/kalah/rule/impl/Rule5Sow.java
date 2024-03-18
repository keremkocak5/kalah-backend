package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.RuleType;
import com.kocak.kalah.rule.Rulable;
import org.springframework.stereotype.Service;

@Service
public class Rule5Sow implements Rulable {

    @Override
    public RuleType applyRule(Game game, int pit) { // kerem dikkat kontrol
        int tokenCountBeforeReset = game.getBoards().get(pit).getTokenCount();
        game.getBoards().get(pit).resetTokenCount();
        RuleType ruleType = RuleType.REGULAR;
        while (tokenCountBeforeReset > 0) {
            ruleType = RuleType.REGULAR;
            pit++;
            if (game.getBoards().get(pit % game.getEffectivePitCount()).isKalah() && !game.getBoards().get(pit % game.getEffectivePitCount()).getPlayerSide().equals(game.getTurn())) { // kerem buralar elden gecsin
                pit++;
            }
            if (game.getBoards().get(pit % game.getEffectivePitCount()).isKalah()) {
                ruleType = RuleType.LAST_PIT_KALAH;
            } else if (!game.getBoards().get(pit % game.getEffectivePitCount()).isKalah() && game.getBoards().get(pit % game.getEffectivePitCount()).getTokenCount() == 0 && game.getBoards().get(pit % game.getEffectivePitCount()).getPlayerSide().equals(game.getTurn())) {
                ruleType = RuleType.LAST_PIT_EMPTY;
            }
            game.getBoards().get(pit % game.getEffectivePitCount()).incrementTokenCount();
            tokenCountBeforeReset--;
        }
        game.setLastIndex(pit - 1 % game.getEffectivePitCount());
        return ruleType;
    }

}
