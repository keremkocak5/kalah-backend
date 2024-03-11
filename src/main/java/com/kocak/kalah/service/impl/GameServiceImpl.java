package com.kocak.kalah.service.impl;

import com.kocak.kalah.enums.PlayerSide;
import com.kocak.kalah.model.dto.incoming.CreateGameRequestDto;
import com.kocak.kalah.model.entity.Board;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.entity.Player;
import com.kocak.kalah.repository.BoardRepository;
import com.kocak.kalah.repository.GameRepository;
import com.kocak.kalah.repository.PlayerRepository;
import com.kocak.kalah.service.GameService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.kocak.kalah.util.Util.initialPitTokenCount;
import static com.kocak.kalah.util.Util.isKalah;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public Game createGame(CreateGameRequestDto createGameDto) {
        try {
            Game game = new Game(createGameDto.pitCount(), createGameDto.againstComputer());
            gameRepository.save(game);
            playerRepository.saveAll(List.of(new Player(game.getId(), PlayerSide.A, createGameDto.playerAName()), new Player(game.getId(), PlayerSide.B, createGameDto.playerBName())));
            createBoard(game, createGameDto.pitCount());
            return game;
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

    // kerem bunu board servisine al useri de oyle
    private void createBoard(Game game, short pitCount) {
        List<Board> boards = new ArrayList<>();
        for (short pit = 1; pit <= 2 + (pitCount * 2); pit++) {
            boards.add(new Board(game, pit, pit <= pitCount+1 ? PlayerSide.A : PlayerSide.B, isKalah(pitCount, pit) ? 0 : initialPitTokenCount, isKalah(pitCount, pit)));
        }
        boardRepository.saveAll(boards);
    }


}
