package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Board;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule.GamePlayRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Sow implements GamePlayRule {

    @Override
    public Game apply(Game game, short pit) {
        List<Board> boards = game.getBoards(); // map yapalim
        int x = boards.get(pit-1).tokenCount;
        boards.get(pit-1).tokenCount = 0;
        boards.stream()
                .filter(board -> !(board.isKalah() && !game.turn.equals(board.playerSide))) //kerem dikkat asagidaki 2 de degisecek
                .forEach(board -> board.tokenCount += incrementBy(x, pit, board.pit, 1 + (game.pitCount * 2)));
        return game;
    }

    private int incrementBy(int tokenCount, int startIndex, int currentIndex, int total) {
        return ( currentIndex != startIndex &&  takeNegativeModulo(currentIndex - startIndex, total) <= tokenCount%total ? 1 : 0) + tokenCount/(total);
    }

    private int takeNegativeModulo(int dividend, int modulo) {
        return 	(((dividend % modulo) + modulo) % modulo);
    }
}
