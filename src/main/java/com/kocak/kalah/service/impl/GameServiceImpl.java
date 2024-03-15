package com.kocak.kalah.service.impl;

import com.kocak.kalah.enums.PlayerSide;
import com.kocak.kalah.model.dto.incoming.CreateGameRequestDto;
import com.kocak.kalah.model.dto.outgoing.BoardResponseDto;
import com.kocak.kalah.model.dto.outgoing.GameResponseDto;
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
import java.util.stream.Collectors;

import static com.kocak.kalah.util.Util.INITIAL_PIT_TOKEN_COUNT;
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
    public GameResponseDto createGame(CreateGameRequestDto createGameDto) {
        try {
            Game game = new Game(createGameDto.pitCount(), createGameDto.againstComputer());
            gameRepository.save(game);
            playerRepository.saveAll(List.of(new Player(game.getId(), PlayerSide.BLUE, createGameDto.playerAName()), new Player(game.getId(), PlayerSide.RED, createGameDto.playerBName())));
            List<Board>  boards = createBoard(game, createGameDto.pitCount());
            return new GameResponseDto(game.getPitCount(), game.getId(), "kerem", "koc", game.getTurn(), game.getStatus(),
                    boards.stream().map(board -> new BoardResponseDto(board.getId(), board.getPit(), board.getTokenCount(), board.getPlayerSide(), board.isKalah())).collect(Collectors.toList()));
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

    // kerem bunu board servisine al useri de oyle
    private List<Board>  createBoard(Game game, short pitCount) {
        List<Board> boards = new ArrayList<>();
        for (short pit = 0; pit <= 1 + (pitCount * 2); pit++) {
            boards.add(new Board(game, pit, pit <= pitCount ? PlayerSide.BLUE : PlayerSide.RED, isKalah(pitCount, pit) ? 0 : INITIAL_PIT_TOKEN_COUNT, isKalah(pitCount, pit)));
        }
        return boardRepository.saveAll(boards);
    }


}
