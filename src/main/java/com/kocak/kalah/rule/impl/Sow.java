package com.kocak.kalah.rule.impl;

import com.kocak.kalah.model.entity.Board;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.repository.BoardRepository;
import com.kocak.kalah.rule.GamePlayRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Sow implements GamePlayRule {

    private final BoardRepository boardRepository;

    @Override
    public Game apply(Game game, short pit) {
        int pitt = new Integer(pit);
        List<Board> boards1 = game.getBoards(); // map yapalim
        Map<Integer, Board> boards = boards1.stream().collect(Collectors.toMap(p-> p.pit, p-> p ));
        System.out.println(boards.get(pitt));
        int tokenCount1 = boards.get(pitt).tokenCount;
        boards.get(pitt).tokenCount = 0;
        int max = (game.getPitCount()*2)+2;
        int i=(pitt%max)+1;//       (pitt+1%max)+1;

        while (tokenCount1 > 0) {
            tokenCount1--;

            if (boards.get(i).kalah && !boards.get(i).playerSide.name().equals(game.getTurn().name())) {

            } else {
                boards.get(i).tokenCount++;
            }
            i=(i%max)+1;
        }

        return game;
    }

    private Board yy(List<Board> b, int pit) {
        return b.stream().filter(board -> board.pit == pit).findFirst().get();
    }

    private int incrementBy(int tokenCount, int startIndex, int currentIndex, int total) {
        return ( currentIndex != startIndex &&  takeNegativeModulo(currentIndex - startIndex, total) <= tokenCount%total ? 1 : 0) + tokenCount/(total);
    }

    private int takeNegativeModulo(int dividend, int modulo) {
        return 	(((dividend % modulo) + modulo) % modulo);
    }
}
