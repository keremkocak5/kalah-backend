package com.kocak.kalah.service.impl;

import com.kocak.kalah.exception.KalahRuntimeException;
import com.kocak.kalah.model.dto.outgoing.BoardHeaderResponseDto;
import com.kocak.kalah.model.dto.outgoing.BoardResponseDto;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.model.enums.ErrorCode;
import com.kocak.kalah.repository.BoardRepository;
import com.kocak.kalah.repository.GameRepository;
import com.kocak.kalah.rule.Ruleable;
import com.kocak.kalah.rule.impl.Rule0GameActive;
import com.kocak.kalah.service.PlayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayServiceImpl implements PlayService {

    private final GameRepository gameRepository;
    private final BoardRepository boardRepository;
    private final Rule0GameActive rule0GameActive;

    @Override
    @Transactional
    public BoardHeaderResponseDto makeMove(long gameId, short pit) {
        try {
            Game game = gameRepository.findById(gameId).orElseThrow(() -> new KalahRuntimeException(ErrorCode.NO_SUCH_GAME_FOUND));
            Ruleable nextRule = rule0GameActive;
            while (nextRule != null) {
                nextRule = nextRule.applyRule(game, new Integer(pit));
            }
            return new BoardHeaderResponseDto(game.getBoards().entrySet().stream().map(board -> new BoardResponseDto( // buraya mapper
                    board.getValue().getId(),
                    board.getValue().getPit(),
                    board.getValue().getTokenCount(),
                    board.getValue().getPlayerSide(),
                    board.getValue().isKalah()
            )).collect(Collectors.toUnmodifiableList()), game.getTurn());
        } catch (Exception e) {
            // kerem burayi doldur ve diglerlerini buna benzet
        }
    }

    @Override
    public BoardHeaderResponseDto getBoard(long gameId) {


        try {
            return new BoardHeaderResponseDto(boardRepository.findByGameId(gameId).stream().map(board -> new BoardResponseDto(
                    board.getId(),
                    board.getPit(),
                    board.getTokenCount(),
                    board.getPlayerSide(),
                    board.isKalah()
            )).collect(Collectors.toUnmodifiableList()), boardRepository.findByGameId(gameId).get(0).getGame().getTurn()); // kerem!!!!!!! dikkat
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            // kerem
            return null;
        }
    }
}
