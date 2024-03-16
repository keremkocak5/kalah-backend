package com.kocak.kalah.rule2;

import com.kocak.kalah.model.entity.Game;
import org.springframework.stereotype.Service;

@Service
public class GameOver implements Ruleable {
    public boolean isRuleMeetsCondition(Game game, int pit) {

        System.out.println("is  game");
        return true;
    }

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
