package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Board;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.GamePlayRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameOver implements GamePlayRule {

    @Override
    public Game apply(Game game, short pit) {
        List<Board> bb = game.getBoards(); // map yapalim
        bb.stream().forEach(x-> x.tokenCount++);
        return game;
    }
}
