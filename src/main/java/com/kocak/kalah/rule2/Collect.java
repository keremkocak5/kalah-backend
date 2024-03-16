package com.kocak.kalah.rule2;

import com.kocak.kalah.model.entity.Game;
import org.springframework.stereotype.Service;

@Service
public class Collect implements Ruleable {
    public boolean isRuleMeetsCondition(Game game, int pit) {
        return true;
    }

    public Game applyRule(Game game, int pit) {
        return game.switchSide();
    }

    @Override
    public int getRuleOrder() {
        return 3;
    }

    @Override
    public int getRuleOrder2() {
        return 0;
    }
}
