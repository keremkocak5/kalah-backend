package com.kocak.kalah.rule2;

import com.kocak.kalah.model.entity.Game;

public interface Ruleable2 {

    public boolean isRuleApplicable(Game game, int pit);

    public Game applyRule(Game game, int pit);

    public int getRuleOrder();

    public int getRuleOrder2();


}
