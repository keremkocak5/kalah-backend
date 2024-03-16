package com.kocak.kalah.rule2;

import com.kocak.kalah.model.entity.Game;

public interface Ruleable {

    public boolean isRuleMeetsCondition(Game game, int pit);

    public Game applyRule(Game game, int pit);

    public int getRuleOrder();

    public int getRuleOrder2();


}
