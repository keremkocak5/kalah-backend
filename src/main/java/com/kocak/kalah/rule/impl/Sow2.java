package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Board;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.repository.BoardRepository;
import com.kocak.kalah.rule.GamePlayRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Sow2 implements GamePlayRule {

    private final BoardRepository boardRepository;

    @Override
    public Game apply(Game game, short pitt) { // kerem coklu hibernate dikkat!!!
        Map<Integer, Board> boards = game.getBoards().stream().collect(Collectors.toMap(p -> p.getPit(), p -> p));
        int pit = new Integer(pitt);
        int tokenCountBeforeReset = boards.get(pit).getTokenCount();
        boards.get(pit).resetTokenCount();

        for (int i = pit+1; i < tokenCountBeforeReset+pit+1; i++) {
            if (boards.get(i % game.getModuloPitCount()).isKalah() && !boards.get(i % game.getModuloPitCount()).getPlayerSide().equals(game.getTurn())) { // kerem buralar elden gecsin
                i++;
            }
            boards.get(i % game.getModuloPitCount()).incrementTokenCount();
        }
        return game;
    }

}
