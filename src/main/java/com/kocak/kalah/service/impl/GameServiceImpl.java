package com.kocak.kalah.service.impl;

import com.kocak.kalah.exception.KalahRuntimeException;
import com.kocak.kalah.model.dto.incoming.CreateGameRequestDto;
import com.kocak.kalah.model.dto.outgoing.BoardResponseDto;
import com.kocak.kalah.model.dto.outgoing.GameResponseDto;
import com.kocak.kalah.model.entity.Board;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.entity.Player;
import com.kocak.kalah.model.enums.ErrorCode;
import com.kocak.kalah.model.enums.PlayerSide;
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

import static com.kocak.kalah.Constants.INITIAL_PIT_TOKEN_COUNT;

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
            Game game = createGameEntity(createGameDto);
            List<Player> players = createPlayerEntities(createGameDto, game);
            List<Board> boards = createBoardEntities(game, createGameDto.pitCount());
            return convertEntitiesToGameResponseDto(game, players, boards);
        } catch (Exception e) {
            log.error("An unexpected exception during createGame. Exception {}", e);
            throw new KalahRuntimeException(ErrorCode.UNKNOWN_ERROR);
        }
    }

    @Override
    public GameResponseDto getGame(long gameId) {
        try {
            Game game = gameRepository.findById(gameId).orElseThrow(() -> new KalahRuntimeException(ErrorCode.NO_SUCH_GAME_FOUND));
            return convertEntitiesToGameResponseDto(game, game.getPlayers(), game.getBoards().values().stream().toList());
        } catch (Exception e) {
            log.error("An unexpected exception during createGame. Exception {}", e);
            throw new KalahRuntimeException(ErrorCode.UNKNOWN_ERROR);
        }
    }

    private GameResponseDto convertEntitiesToGameResponseDto(Game game, List<Player> players, List<Board> boards) {
        return new GameResponseDto(
                game.getPitCount(),
                game.getId(),
                players.stream().filter(player -> player.isPlayerRed()).findFirst().map(player -> player.getPlayerName()).get(),
                players.stream().filter(player -> player.isPlayerBlue()).findFirst().map(player -> player.getPlayerName()).get(),
                game.getTurn(),
                game.getStatus(),
                boards.stream()
                        .map(board -> new BoardResponseDto(board.getId(),
                                board.getPit(),
                                board.getTokenCount(),
                                board.getPlayerSide(),
                                board.isKalah()))
                        .collect(Collectors.toUnmodifiableList()));
    }

    private List<Player> createPlayerEntities(CreateGameRequestDto createGameDto, Game game) {
        return playerRepository.saveAll(
                List.of(
                        new Player(game, PlayerSide.BLUE, createGameDto.playerBlueName()),
                        new Player(game, PlayerSide.RED, createGameDto.playerRedName())
                ));
    }

    private Game createGameEntity(CreateGameRequestDto createGameDto) {
        Game game = new Game(createGameDto.pitCount());
        return gameRepository.save(game);
    }

    // kerem bunu board servisine al useri de oyle
    private List<Board> createBoardEntities(Game game, int pitCount) {
        List<Board> boards = new ArrayList<>();
        for (int pit = 0; pit <= 1 + (pitCount * 2); pit++) {
            boards.add(new Board(game, pit, pit <= pitCount ? PlayerSide.BLUE : PlayerSide.RED, game.isPitKalah(pit) ? 0 : INITIAL_PIT_TOKEN_COUNT, game.isPitKalah(pit)));
        }
        return boardRepository.saveAll(boards);
    }


}
