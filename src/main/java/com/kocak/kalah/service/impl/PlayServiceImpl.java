package com.kocak.kalah.service.impl;

import com.kocak.kalah.model.dto.outgoing.BoardHeaderResponseDto;
import com.kocak.kalah.model.dto.outgoing.BoardResponseDto;
import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.repository.BoardRepository;
import com.kocak.kalah.repository.GameRepository;
import com.kocak.kalah.rule.Ruleable;
import com.kocak.kalah.rule.impl.Rule0GameActive;
import com.kocak.kalah.service.PlayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
        // gelen talebi valide et: oyun var mi?
        // oyun varsa, talep edilen pit numarasi kisiye ozel mi? kisiye ozelse, dolu mu? oyun statusu nedir
        // oyna.
        // kontrolleri yap, ucluyu calistir
        // oyuncu degismesi gerekiyorsa degistir
        // oyun bitti mi diye kontrol et. kazanma varsa kazanma operasyonu
        // board geri dondur
        Optional<Game> game = gameRepository.findById(gameId);
        Ruleable ruleable = rule0GameActive;
        while (ruleable != null) {
            ruleable = ruleable.applyRule(game.get(), new Integer(pit));
        }
        return new BoardHeaderResponseDto(game.get().getBoards().stream().map(board -> new BoardResponseDto( // buraya mapper
                board.getId(),
                board.getPit(),
                board.getTokenCount(),
                board.getPlayerSide(),
                board.isKalah()
        )).collect(Collectors.toUnmodifiableList()), game.get().getTurn());
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
