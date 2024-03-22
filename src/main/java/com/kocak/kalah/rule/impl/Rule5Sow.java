package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.RuleType;
import com.kocak.kalah.rule.Rulable;
import org.springframework.stereotype.Service;

@Service
public class Rule5Sow implements Rulable {

    @Override
    public RuleType applyRule(Game game, int pit) {
        int tokenCountBeforeReset = game.getBoards().get(pit).getTokenCount();
        game.getBoards().get(pit).resetTokenCount();
        RuleType lastPitRuleType = RuleType.REGULAR;
        while (tokenCountBeforeReset > 0) {
            pit = skipPitIfOtherPlayersKalah(game, ++pit);
            lastPitRuleType = getLastPitRuleType(game, pit, tokenCountBeforeReset);
            game.getBoards().get(pit % game.getEffectivePitCount()).incrementTokenCount();
            tokenCountBeforeReset--;
        }
        game.setLastIndex(pit % game.getEffectivePitCount());
        return lastPitRuleType;
    }

    private RuleType getLastPitRuleType(Game game, int pit, int tokenCountBeforeReset) {
        if (tokenCountBeforeReset == 1) {
            if (game.getBoards().get(pit % game.getEffectivePitCount()).isKalah()) {
                return RuleType.LAST_PIT_KALAH;
            } else if (game.getBoards().get(pit % game.getEffectivePitCount()).getTokenCount() == 0 && game.getBoards().get(pit % game.getEffectivePitCount()).getPlayerSide().equals(game.getTurn())) {
                return RuleType.LAST_PIT_EMPTY;
            } else {
                return RuleType.REGULAR;
            }
        }
        return RuleType.REGULAR;
    }

    private int skipPitIfOtherPlayersKalah(Game game, int pit) {
        if (game.getBoards().get(pit % game.getEffectivePitCount()).isKalah() && !game.getBoards().get(pit % game.getEffectivePitCount()).getPlayerSide().equals(game.getTurn())) { // kerem buralar elden gecsin
            pit++;
        }
        return pit;
    }

}
