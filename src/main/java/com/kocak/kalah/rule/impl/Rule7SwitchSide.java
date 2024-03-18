package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.RuleType;
import com.kocak.kalah.rule.Rulable;
import org.springframework.stereotype.Service;

@Service
public class Rule7SwitchSide implements Rulable {

    @Override
    public RuleType applyRule(Game game, int pit) {
        game.switchSide();
        return RuleType.REGULAR;
    }

}