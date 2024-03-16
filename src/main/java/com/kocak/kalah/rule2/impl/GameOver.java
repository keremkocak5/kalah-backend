package com.kocak.kalah.rule2.impl;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule2.Ruleable2;
import org.springframework.stereotype.Service;

@Service
public class GameOver implements Ruleable2 {

    @Override
    public boolean isRuleApplicable(Game game, int pit) {

        System.out.println("is  game");
        return true;
    }

    @Override
    public Game applyRule(Game game, int pit) {
        System.out.println("apply  game");
        return game.switchSide();
    }

    @Override
    public int getRuleOrder() {
        return 4;
    }

    @Override
    public int getRuleOrder2() {
        return 3;
    }
}
