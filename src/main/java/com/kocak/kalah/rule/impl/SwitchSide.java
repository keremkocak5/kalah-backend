package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Board;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.GamePlayRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwitchSide implements GamePlayRule {


    @Override
    public Game apply(Game game, short pit) {
        if(game.getBoards().get(pit).getTokenCount() == game.getPitCount()-pit) {
            // switch side
        } else {
            // dont switch side
        }
        return null;
    }
}
