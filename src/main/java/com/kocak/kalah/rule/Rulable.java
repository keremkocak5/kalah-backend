package com.kocak.kalah.rule;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.RuleType;

public interface Rulable {

    RuleType applyRule(Game game, int pit);

}
